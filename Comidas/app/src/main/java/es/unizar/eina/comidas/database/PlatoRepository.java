package es.unizar.eina.comidas.database;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio que actúa como una capa de abstracción entre las fuentes de datos (como la base de
 * datos Room) y la interfaz de usuario. Provee métodos para interactuar con los platos en la base
 * de datos.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
public class PlatoRepository {

    /** Objeto de acceso a datos (DAO) para la entidad Plato. */
    private PlatoDao mPlatoDao;
    /** LiveData que contiene la lista de todos los platos ordenados. */
    private LiveData<List<Plato>> mAllPlatos;

    private final long TIMEOUT = 15000;

    /*
    Note that in order to unit test the PlatoRepository, you have to remove the Application
    dependency. This adds complexity and much more code, and this sample is not about testing.
    See the BasicSample in the android-architecture-components repository at
    https://github.com/googlesamples
    */

    /**
     * Constructor de la clase que inicializa el DAO y obtiene la lista de todos los platos.
     *
     * @param context La aplicación en la que se encuentra el repositorio.
     */
    public PlatoRepository(Context context) {
        ComidasRoomDatabase db = ComidasRoomDatabase.getDatabase(context);
        mPlatoDao = db.platoDao();
        mAllPlatos = mPlatoDao.getAllPlatos();
    }


    private Boolean validarPlato(Plato plato){
        boolean valor = true;

        String primero = "PRIMERO";
        String segundo = "SEGUNDO";
        String postre = "POSTRE";

        String nombre = plato.getNombre();
        String categoria = plato.getCategoria();
        Double precioPlato = plato.getPrecio();

        //Huecos vacios
        if(nombre.isEmpty() || categoria.isEmpty()) {
            valor = false;
        } else if (!(categoria.equals(primero)
                || categoria.equals(segundo) || categoria.equals(postre))) { //Categoria valida
            valor = false;
        } else if (precioPlato < 0) { //Precio valido
            valor = false;
        }
        return valor;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    /**
     * Obtiene LiveData que contiene la lista de todos los platos ordenados.
     *
     * @return LiveData que contiene la lista de todos los platos ordenados.
     */
    public LiveData<List<Plato>> getAllPlatos() {
        return mAllPlatos;
    }

    Throwable mException;

    public Throwable getException() {
        return mException;
    }

    /**
     * Inserta un nuevo plato en la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato que se va a insertar.
     * @return El identificador único del plato recién insertado.
     */
    public long insert(Plato plato) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value;
            try {
                if (validarPlato(plato)) {
                    value = mPlatoDao.insert(plato);
                    if (value == -1) {
                        throw new RuntimeException("Error al insertar el plato. El valor devuelto fue -1.");
                    }
                } else {
                    throw new RuntimeException("Error al insertar el plato. Validación del plato fallida.");
                }
                result.set(value);
                resource.release();
            } catch (Throwable throwable) {
            mException = throwable; // Almacenar la excepción
        }
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            Log.d("PlatoRepository", "InterruptedException en Insert: " + e.getMessage());
        }
        return result.get();
    }

    /**
     * Actualiza un plato existente en la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato actualizado que se va a almacenar.
     * @return El número de filas afectadas en la base de datos.
     */
    public int update(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            int value;
            try {
                if (validarPlato(plato)) {
                    value = mPlatoDao.update(plato);
                    if (value == -1) {
                        throw new RuntimeException("Error al insertar el plato. El valor devuelto fue -1.");
                    }
                } else {
                    throw new RuntimeException("Error al insertar el plato. Validación del plato fallida.");
                }
                result.set(value);
                resource.release();
            } catch (Throwable throwable) {
                mException = throwable; // Almacenar la excepción
            }
        });
        try{
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            Log.d("PlatoRepository", "InterruptedExecution: " + e.getMessage());
        }
        return result.get();
    }

    /**
     * Elimina un plato de la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato que se va a eliminar.
     * @return El número de filas afectadas en la base de datos.
     */
    public int delete(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPlatoDao.delete(plato));
            resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            Log.d("PlatoRepository", "InterruptedException en Insert: " + e.getMessage());
        }
        return result.get();
    }
}
