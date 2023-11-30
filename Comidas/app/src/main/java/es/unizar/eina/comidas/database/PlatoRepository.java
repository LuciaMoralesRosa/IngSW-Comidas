package es.unizar.eina.comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    //private int mNumeroDePlatos;

    /*
    Note that in order to unit test the PlatoRepository, you have to remove the Application
    dependency. This adds complexity and much more code, and this sample is not about testing.
    See the BasicSample in the android-architecture-components repository at
    https://github.com/googlesamples
    */

    /**
     * Constructor de la clase que inicializa el DAO y obtiene la lista de todos los platos.
     *
     * @param application La aplicación en la que se encuentra el repositorio.
     */
    public PlatoRepository(Application application) {
        PlatoRoomDatabase db = PlatoRoomDatabase.getDatabase(application);
        mPlatoDao = db.platoDao();
        mAllPlatos = mPlatoDao.getAllPlatos();
    //    mNumeroDePlatos = mPlatoDao.getNumeroDePlatosDB();
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

    //public int getNumeroDePlatos(){
    //    return mNumeroDePlatos;
    //}

    /**
     * Inserta un nuevo plato en la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato que se va a insertar.
     * @return El identificador único del plato recién insertado.
     */
    public long insert(Plato plato) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.insert(plato);
        });
        return result[0];
    }

    /**
     * Actualiza un plato existente en la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato actualizado que se va a almacenar.
     * @return El número de filas afectadas en la base de datos.
     */
    public int update(Plato plato) {
        final int[] result = {0};
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.update(plato);
        });
        return result[0];
    }

    /**
     * Elimina un plato de la base de datos utilizando un hilo de ejecución separado.
     *
     * @param plato El plato que se va a eliminar.
     * @return El número de filas afectadas en la base de datos.
     */
    public int delete(Plato plato) {
        final int[] result = {0};
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.delete(plato);
        });
        return result[0];
    }
}
