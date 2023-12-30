package es.unizar.eina.comidas.T234_Platos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.junit.Test;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.appcompat.app.AppCompatActivity;

import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.comidas.database.PlatoRepository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest  extends AppCompatActivity {
    private Context mContext;
    public UnitTest(Context context){
        super();
        mContext = context;
    }

    @Test
    private void insertTestPlato(){
        //Context context = getApplicationContext();
        PlatoRepository mPlatoRepository = new PlatoRepository(mContext);
/*
        //Caso 1
        try{
            //Insert plato correcto con descripcion
            Plato prueba1 = new Plato("Plato de prueba", "Descripcion",
                    "PRIMERO", 12.5);
            mPlatoRepository.insert(prueba1);
            Log.d("UnitTests", "Prueba 1: Insercion correcta de un plato con descripcion." +
                    "\nHa dado un valor correcto, como se esperaba");
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.e("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage());
            fail("Se lanzó una excepción: " + throwable.getMessage());
        }

        // Caso 2
        try{
            //Insert plato correcto sin descripcion
            Plato prueba2 = new Plato("Plato de prueba", "", "POSTRE", 12.5);
            mPlatoRepository.insert(prueba2);
            Log.d("UnitTests", "Prueba 2: Insercion correcta de un plato sin descripcion"+
                    "\nHa dado un valor correcto, como se esperaba");
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.e("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage());
            fail("Se lanzó una excepción: " + throwable.getMessage());
        }

        // Caso 3
        try{
            //Insert plato correcto sin descripcion
            Plato prueba3 = new Plato("Plato de prueba", "", "SEGUNDO", 12.5);
            mPlatoRepository.insert(prueba3);
            Log.d("UnitTests", "Prueba 3: Insercion correcta de un plato con categoria segundo"+
                    "\nHa dado un valor correcto, como se esperaba");
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.e("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage());
            fail("Se lanzó una excepción: " + throwable.getMessage());
        }
*/
        // Caso 4
        try{
            //Insert plato correcto sin descripcion
            Plato prueba4 = new Plato("", "", "POSTRE", 12.5);
            mPlatoRepository.insert(prueba4);
            Log.d("UnitTests", "Prueba 4: Insercion incorrecta de un plato sin nombre");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException(); // Lanzar la excepción almacenada
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                    + "\nHa dado un valor incorrecto, como se esperaba");
            //fail("Se lanzó una excepción: " + throwable.getMessage());
        }

    }

    public void runAllTests(){
        insertTestPlato();
    }

}
