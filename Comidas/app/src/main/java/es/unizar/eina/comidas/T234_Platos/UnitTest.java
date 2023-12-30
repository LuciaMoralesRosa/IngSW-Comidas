package es.unizar.eina.comidas.T234_Platos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.junit.Test;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.comidas.database.PlatoRepository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest  extends AppCompatActivity {

    private PlatoRepository mPlatoRepository;
    private Context mContext;

    public UnitTest(Context context){
        super();
        mContext = context;
    }

    @Test
    private void insertTestPlato() {
        ArrayList<Plato> platosDePrueba = new ArrayList<>();
/*
        //Caso 1 : Insert plato correcto con descripcion
        Plato prueba1 = new Plato("Plato de prueba", "Descripcion",
                "PRIMERO", 12.5);
        platosDePrueba.add(prueba1);
        try {
            int id =(int) mPlatoRepository.insert(prueba1);
            prueba1.setId(id);
            mPlatoRepository.delete(prueba1);
            Log.d("UnitTests", "Prueba 1: Insercion correcta de un plato con descripcion." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor no esperado");
        }

        // Caso 2 : Insert plato correcto sin descripcion
        Plato prueba2 = new Plato("Plato de prueba", "", "POSTRE", 12.5);
        platosDePrueba.add(prueba2);
        try {
            int id = (int) mPlatoRepository.insert(prueba2);
            prueba2.setId(id);
            Log.d("UnitTests", "Prueba 2: Insercion correcta de un plato sin descripcion" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor no esperado");
        }

        // Caso 3 : Insert plato correcto sin descripcion
        Plato prueba3 = new Plato("Plato de prueba", "", "SEGUNDO", 12.5);
        platosDePrueba.add(prueba3);
        try {
            int id = (int) mPlatoRepository.insert(prueba3);
            prueba3.setId(id);
            Log.d("UnitTests", "Prueba 3: Insercion correcta de un plato con categoria segundo" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor no esperado");
        }

        // Caso 4 : Insert plato incorrecto sin nombre
        Plato prueba4 = new Plato("", "", "POSTRE", 12.5);
        platosDePrueba.add(prueba4);
        try {
            int id = (int) mPlatoRepository.insert(prueba4);
            prueba4.setId(id);
            Log.d("UnitTests", "Prueba 4: Insercion incorrecta de un plato sin nombre");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
        }

        // Caso 5 : Insert plato incorrecto sin categoria
        Plato prueba5 = new Plato("Plato de prueba", "", "", 12.5);
        platosDePrueba.add(prueba5);
        try {
            int id = (int) mPlatoRepository.insert(prueba5);
            prueba5.setId(id);
            Log.d("UnitTests", "Prueba 5: Insercion incorrecta de un plato sin categoria");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
        }

        // Caso 6 : Insert plato incorrecto con categoria no valida
        Plato prueba6 = new Plato("Plato de prueba", "", "OTRO", 12.5);
        platosDePrueba.add(prueba6);
        try {
            int id = (int) mPlatoRepository.insert(prueba6);
            prueba6.setId(id);
            Log.d("UnitTests", "Prueba 6: Insercion incorrecta de un plato con categoria incorrecta");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
        }

        // Caso 7 : Insert plato incorrecto con precio negativo
        Plato prueba7 = new Plato("Plato de prueba", "", "POSTRE", -1.1);
        platosDePrueba.add(prueba7);
        try {
            int id = (int) mPlatoRepository.insert(prueba7);
            prueba7.setId(id);
            Log.d("UnitTests", "Prueba 7: Insercion incorrecta de un plato con precio negativo");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
        }
*/
        // Caso 7 : Insert plato incorrecto con precio negativo
        Plato prueba8 = new Plato("null", null, "POSTRE", -1.1);
        platosDePrueba.add(prueba8);
        try {
            int id = (int) mPlatoRepository.insert(prueba8);
            prueba8.setId(id);
            Log.d("UnitTests", "Prueba 8: Insercion incorrecta de un plato con precio negativo");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
        }

        //Eliminacion de platos creados para la prueba
        long valorDelete;
        for(Plato plato : platosDePrueba){
            valorDelete = mPlatoRepository.delete(plato);
        }
        Log.d("UnitTests", "Se han eliminado los platos generados en la prueba");
    }


    private void updateTestPlato(){
        //Creacion del plato a modificar
        Plato platoModificar = new Plato("Plato a modificar", "",
                "PRIMERO", 12.5);
        int id = (int) mPlatoRepository.insert(platoModificar);
        platoModificar.setId(id);

        //Caso 1 : Update plato correcto con descripcion
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("PRIMERO");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 1: Modificacion correcta de un plato con descripcion." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\n\nHa dado un valor no esperado");
        }

        //Caso 2 : Update plato correcto sin descripcion y categoria "SEGUNDO"
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("");
        platoModificar.setCategoria("SEGUNDO");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 2: Modificacion correcta de un plato sin descripcion." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\n\nHa dado un valor no esperado");
        }

        //Caso 3 : Update plato correcto con descripcion y categoria "POSTRE"
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("PRIMERO");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 3: Modificacion correcta de un plato con descripcion y categoria \"POSTRE\"." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage() +
                    "\nHa dado un valor no esperado");
        }

        //Caso 4 : Update plato incorrecto sin nombre
        platoModificar.setNombre("");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("PRIMERO");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 4: Modificacion incorrecta de un plato sin nombre");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage() +
                    "\nHa dado un valor incorrecto, como se esperaba");
        }

        //Caso 5 : Update plato incorrecto sin categoria
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 5: Modificacion incorrecta de un plato sin categoria");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage() +
                    "\nHa dado un valor incorrecto, como se esperaba");
        }

        //Caso 6 : Update plato incorrecto con categoria no valida
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("OTRO");
        platoModificar.setPrecio(12.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 6: Modificacion incorrecta de un plato con categoria no valida");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage() +
                    "\nHa dado un valor incorrecto, como se esperaba");
        }

        //Caso 7 : Update plato incorrecto con precio negativo
        platoModificar.setNombre("Modificado");
        platoModificar.setDescripcion("Desc");
        platoModificar.setCategoria("PRIMERO");
        platoModificar.setPrecio(-1.0);
        try {
            mPlatoRepository.update(platoModificar);
            Log.d("UnitTests", "Prueba 7: Modificacion incorrecta de un plato con precio negativo");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage() +
                    "\nHa dado un valor incorrecto, como se esperaba");
        }

        //Eliminacion del plato creado para la prueba
        mPlatoRepository.delete(platoModificar);
        Log.d("UnitTests", "Se han eliminado los platos generados en la prueba");
    }

    private void deleteTestPlato() {
        //Creacion del plato a eliminar
        Plato platoModificar = new Plato("Plato a eliminar", "",
                "PRIMERO", 12.5);
        int id = (int) mPlatoRepository.insert(platoModificar);
        platoModificar.setId(id);



    }

    public void runAllTests(){
        mPlatoRepository = new PlatoRepository(mContext);
        insertTestPlato();
        //updateTestPlato();
        //deleteTestPlato();
    }

}
