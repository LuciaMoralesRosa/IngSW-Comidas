package es.unizar.eina.comidas.T234_Platos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.junit.Test;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.comidas.database.Pedido;
import es.unizar.eina.comidas.database.PedidoRepository;
import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.comidas.database.PlatoRepository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest  extends AppCompatActivity {

    private PlatoRepository mPlatoRepository;
    private PedidoRepository mPedidoRepository;
    private Context mContext;

    public UnitTest(Context context){
        super();
        mContext = context;
    }

    private void showLogTrowableValorNoEsperado(Throwable throwable){
        Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                + "\nHa dado un valor no esperado");
    }
    private void showLogTrowableValorEsperado(Throwable throwable){
        Log.d("UnitTests", "Error en la ejecución de la prueba: " + throwable.getMessage()
                + "\nHa dado un valor incorrecto, como se esperaba");
    }

    @Test
    private void insertTestPlato() {

        ArrayList<Plato> platosDePrueba = new ArrayList<>();

        // Casos de prueba para las clases de equivalencia validas

        // Caso 1 : Insert correcto - nombre = "Plato de prueba", descripcion = "", categoria = "PRIMERO", precio = 12.5
        Plato prueba1 = new Plato("Plato de prueba", "",
                "PRIMERO", 12.5);
        platosDePrueba.add(prueba1);
        try {
            prueba1.setId((int) mPlatoRepository.insert(prueba1));
            Log.d("UnitTests", "Prueba 1: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion \"\", categoria = \"PRIMERO\", precio = 12.5 ." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 2 : Insert correcto - nombre = "Plato de prueba", descripcion = "Desc", categoria = "SEGUNDO", precio = 0.0
        Plato prueba2 = new Plato("Plato de prueba", "Desc",
                "SEGUNDO", 0.0);
        platosDePrueba.add(prueba2);
        try {
            prueba2.setId((int) mPlatoRepository.insert(prueba2));
            Log.d("UnitTests", "Prueba 2: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion = \"Desc\", categoria = \"SEGUNDO\", precio = 0.0" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 3 : Insert correcto - nombre = "Plato de prueba", descripcion = "Desc", categoria = "POSTRE", precio = 12.5
        Plato prueba3 = new Plato("Plato de prueba", "Desc",
                "POSTRE", 12.5);
        platosDePrueba.add(prueba3);
        try {
            prueba3.setId((int) mPlatoRepository.insert(prueba3));
            Log.d("UnitTests", "Prueba 3: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion = \"Desc\", categoria = \"POSTRE\", precio = 12.5" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }


        // Casos de prueba para las clases de equivalencia no validas

        // Caso 4 : Insert incorrecto - nombre = "", descripcion = "Desc", categoria = "POSTRE", precio = 12.5
        Plato prueba4 = new Plato("", "Desc",
                "POSTRE", 12.5);
        platosDePrueba.add(prueba4);
        try {
            prueba4.setId((int) mPlatoRepository.insert(prueba4));
            Log.d("UnitTests", "Prueba 4: Insert incorrecto - nombre = \"\", descripcion" +
                    " = \"Desc\", categoria = \"POSTRE\", precio = 12.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 5 : Insert incorrecto - nombre = null, descripcion = "Desc", categoria = "POSTRE", precio = 12.5
        Plato prueba5 = new Plato(null, "Desc",
                "POSTRE", 12.5);
        platosDePrueba.add(prueba5);
        try {
            prueba5.setId((int) mPlatoRepository.insert(prueba5));
            Log.d("UnitTests", "Prueba 5: Insert incorrecto - nombre = null, descripcion" +
                    " = \"Desc\", categoria = \"POSTRE\", precio = 12.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 6 : Insert incorrecto - nombre = "Plato de prueba", descripcion = null, categoria = "POSTRE", precio = 12.5
        Plato prueba6 = new Plato("Plato de prueba", null,
                "POSTRE", 12.5);
        platosDePrueba.add(prueba6);
        try {
            prueba6.setId((int) mPlatoRepository.insert(prueba6));
            Log.d("UnitTests", "Prueba 6 : Insert incorrecto - nombre = \"Plato de" +
                    " prueba\", descripcion = null, categoria = \"POSTRE\", precio = 12.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 7 : Insert incorrecto - nombre = "Plato de prueba", descripcion = "Desc", categoria = null, precio = 12.5
        Plato prueba7 = new Plato("Plato de prueba", "Desc",
                null, 12.5);
        platosDePrueba.add(prueba7);
        try {
            prueba7.setId((int) mPlatoRepository.insert(prueba7));
            Log.d("UnitTests", "Prueba 7 : Insert incorrecto - nombre = \"Plato de " +
                    "prueba\", descripcion = \"Desc\", categoria = null, precio = 12.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 8 : Insert incorrecto - nombre = "Plato de prueba", descripcion = "Desc", categoria = "OTRO", precio = 12.5
        Plato prueba8 = new Plato("Plato de prueba", "Desc",
                "OTRO", 12.5);
        platosDePrueba.add(prueba8);
        try {
            prueba8.setId((int) mPlatoRepository.insert(prueba8));
            Log.d("UnitTests", "Prueba 8 : Insert incorrecto - nombre = \"Plato de" +
                    " prueba\", descripcion = \"Desc\", categoria = \"OTRO\", precio = 12.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 9 : Insert incorrecto - nombre = "Plato de prueba", descripcion = "Desc", categoria = "POSTRE", precio = -1.5
        Plato prueba9 = new Plato("Plato de prueba", "Desc",
                "POSTRE", -1.5);
        platosDePrueba.add(prueba9);
        try {
            prueba9.setId((int) mPlatoRepository.insert(prueba9));
            Log.d("UnitTests", "Prueba 9 : Insert incorrecto - nombre = \"Plato de" +
                    " prueba\", descripcion = \"Desc\", categoria = \"OTRO\", precio = -1.5");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 10 : Insert incorrecto - nombre = "Plato de prueba", descripcion = "Desc", categoria = "POSTRE", precio = null
        Plato prueba10 = new Plato("Plato de prueba", "Desc",
                "POSTRE", null);
        platosDePrueba.add(prueba10);
        try {
            prueba10.setId((int) mPlatoRepository.insert(prueba10));
            Log.d("UnitTests", "Prueba 10 : Insert incorrecto - nombre = \"Plato de" +
                    " prueba\", descripcion = \"Desc\", categoria = \"POSTRE\", precio = null");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Eliminacion de platos creados para la prueba
        for(Plato plato : platosDePrueba){
            mPlatoRepository.delete(plato);
        }
        Log.d("UnitTests", "Se han eliminado los platos generados en la prueba de INSERT");
    }

    private void updateTestPlato(){

        //Creacion del plato a modificar
        Plato plato = new Plato("Plato a modificar", "",
                "PRIMERO", 12.5);
        int id = (int) mPlatoRepository.insert(plato);
        plato.setId(id);


        // Casos de prueba para las clases de equivalencia validas

        //Caso 1
        plato.setNombre("Modificado");
        plato.setDescripcion("");
        plato.setCategoria("PRIMERO");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 1: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 2
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("SEGUNDO");
        plato.setPrecio(0.0);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 2: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 3
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 3: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }


        // Casos de prueba para las clases de equivalencia no validas

        //Caso 4
        plato.setNombre("");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 4: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 5
        plato.setNombre(null);
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 5: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 6
        plato.setNombre("Modificado");
        plato.setDescripcion(null);
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 6: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 7
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria(null);
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 7: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 8
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("OTRO");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 8: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 9
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(-1.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 9: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 10
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(null);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 10: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 12
        plato.setId(1234567);
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 11: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 14
        plato.setId(-1);
        plato.setNombre("Modificado");
        plato.setDescripcion("Desc");
        plato.setCategoria("POSTRE");
        plato.setPrecio(12.5);
        try {
            mPlatoRepository.update(plato);
            Log.d("UnitTests", "Prueba 12: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Eliminacion del plato creado para la prueba
        plato.setId(id);
        mPlatoRepository.delete(plato);
        Log.d("UnitTests", "Se han eliminado los platos generados en la prueba");
    }

    private void deleteTestPlato() {
        //Creacion del plato a eliminar
        Plato plato = new Plato("Plato a eliminar", "",
                "PRIMERO", 12.5);
        plato.setId((int) mPlatoRepository.insert(plato));

        //Caso 1
        try {
            mPlatoRepository.delete(plato);
            Log.d("UnitTests", "Prueba 1: Delete correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 2
        try {
            plato.setId(12345);
            mPlatoRepository.delete(plato);
            Log.d("UnitTests", "Prueba 2: Delete incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 4
        try {
            plato.setId(-1);
            mPlatoRepository.delete(plato);
            Log.d("UnitTests", "Prueba 3: Delete incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }
    }

    private void insertTestPedido(){
        ArrayList<Pedido> pedidosDePrueba = new ArrayList<>();

        // Casos de prueba para las clases de equivalencia validas

        // Caso 1 : Insert correcto
        Pedido prueba1 = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba1);
        try {
            prueba1.setId((int) mPedidoRepository.insert(prueba1));
            Log.d("UnitTests", "Prueba 1: Insert correcto" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 2 : Insert correcto
        Pedido prueba2 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "20:00",
                0.0);
        pedidosDePrueba.add(prueba2);
        try {
            prueba2.setId((int) mPedidoRepository.insert(prueba2));
            Log.d("UnitTests", "Prueba 2: Insert correcto" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 3 : Insert correcto
        Pedido prueba3 = new Pedido("Nombre", 123456789,
                "RECOGIDO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba3);
        try {
            prueba3.setId((int) mPedidoRepository.insert(prueba3));
            Log.d("UnitTests", "Prueba 3: Insert correcto" +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 4 : Insert incorrecto
        Pedido prueba4 = new Pedido("", 123456789,
                "PREPARADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba4);
        try {
            prueba4.setId((int) mPedidoRepository.insert(prueba4));
            Log.d("UnitTests", "Prueba 4: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 5 : Insert incorrecto
        Pedido prueba5 = new Pedido(null, 123456789,
                "PREPARADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba5);
        try {
            prueba5.setId((int) mPedidoRepository.insert(prueba5));
            Log.d("UnitTests", "Prueba 5: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 6 : Insert incorrecto
        Pedido prueba6 = new Pedido("Nombre", null,
                "PREPARADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba6);
        try {
            prueba6.setId((int) mPedidoRepository.insert(prueba6));
            Log.d("UnitTests", "Prueba 6: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 7 : Insert incorrecto
        Pedido prueba7 = new Pedido("Nombre", 12345678,
                "PREPARADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba7);
        try {
            prueba7.setId((int) mPedidoRepository.insert(prueba7));
            Log.d("UnitTests", "Prueba 7: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 8 : Insert incorrecto
        Pedido prueba8 = new Pedido("Nombre", 1111111111,
                "PREPARADO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba8);
        try {
            prueba8.setId((int) mPedidoRepository.insert(prueba8));
            Log.d("UnitTests", "Prueba 8: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 10 : Insert incorrecto
        Pedido prueba10 = new Pedido("Nombre", 123456789,
                null, "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba10);
        try {
            prueba10.setId((int) mPedidoRepository.insert(prueba10));
            Log.d("UnitTests", "Prueba 10: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 11 : Insert incorrecto
        Pedido prueba11 = new Pedido("Nombre", 123456789,
                "OTRO", "2024/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba11);
        try {
            prueba11.setId((int) mPedidoRepository.insert(prueba11));
            Log.d("UnitTests", "Prueba 11: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 12 : Insert incorrecto
        Pedido prueba12 = new Pedido("Nombre", 123456789,
                "PREPARADO", null, "20:00",
                20.0);
        pedidosDePrueba.add(prueba12);
        try {
            prueba12.setId((int) mPedidoRepository.insert(prueba12));
            Log.d("UnitTests", "Prueba 12: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 13 : Insert incorrecto
        Pedido prueba13 = new Pedido("Nombre", 123456789,
                "PREPARADO", "202/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba13);
        try {
            prueba13.setId((int) mPedidoRepository.insert(prueba13));
            Log.d("UnitTests", "Prueba 13: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 14 : Insert incorrecto
        Pedido prueba14 = new Pedido("Nombre", 123456789,
                "PREPARADO", "1111/11/111", "20:00",
                20.0);
        pedidosDePrueba.add(prueba14);
        try {
            prueba14.setId((int) mPedidoRepository.insert(prueba14));
            Log.d("UnitTests", "Prueba 14: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 15 : Insert incorrecto
        Pedido prueba15 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/01", "20:00",
                20.0);
        pedidosDePrueba.add(prueba15);
        try {
            prueba15.setId((int) mPedidoRepository.insert(prueba15));
            Log.d("UnitTests", "Prueba 15: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 16 : Insert incorrecto
        Pedido prueba16 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2023/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba16);
        try {
            prueba16.setId((int) mPedidoRepository.insert(prueba16));
            Log.d("UnitTests", "Prueba 16: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 17 : Insert incorrecto
        Pedido prueba17 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", null,
                20.0);
        pedidosDePrueba.add(prueba17);
        try {
            prueba17.setId((int) mPedidoRepository.insert(prueba17));
            Log.d("UnitTests", "Prueba 17: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 18 : Insert incorrecto
        Pedido prueba18 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "20:0",
                20.0);
        pedidosDePrueba.add(prueba18);
        try {
            prueba18.setId((int) mPedidoRepository.insert(prueba18));
            Log.d("UnitTests", "Prueba 18: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 19 : Insert incorrecto
        Pedido prueba19 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "20:111",
                20.0);
        pedidosDePrueba.add(prueba19);
        try {
            prueba19.setId((int) mPedidoRepository.insert(prueba19));
            Log.d("UnitTests", "Prueba 19: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 20 : Insert incorrecto
        Pedido prueba20 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "19:00",
                20.0);
        pedidosDePrueba.add(prueba20);
        try {
            prueba20.setId((int) mPedidoRepository.insert(prueba20));
            Log.d("UnitTests", "Prueba 20: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 21 : Insert incorrecto
        Pedido prueba21 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "20:00",
                -1.5);
        pedidosDePrueba.add(prueba21);
        try {
            prueba21.setId((int) mPedidoRepository.insert(prueba21));
            Log.d("UnitTests", "Prueba 21: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 22 : Insert incorrecto
        Pedido prueba22 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2024/01/02", "20:00",
                null);
        pedidosDePrueba.add(prueba22);
        try {
            prueba22.setId((int) mPedidoRepository.insert(prueba22));
            Log.d("UnitTests", "Prueba 22: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Eliminacion de pedidos de prueba
        for(Pedido pedido : pedidosDePrueba){
            mPedidoRepository.delete(pedido);
        }
    }

    private void updateTestPedido(){
        //Creacion del pedido a modificar
        Pedido pedido = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2024/01/02", "20:00",
                20.0);
        int id = (int) mPedidoRepository.insert(pedido);
        pedido.setId(id);

        // Casos de prueba para las clases de equivalencia validas

        //Caso 1
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("SOLICITADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 1: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 2
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(0.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 2: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 3
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("RECOGIDO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 3: Update correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }


        // Casos de prueba para las clases de equivalencia no validas

        //Caso 4
        pedido.setNombreCliente("");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 4: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 5
        pedido.setNombreCliente(null);
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 5: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 6
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(null);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 6: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 7
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(12345678);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 7: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 8
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(111111111);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 8: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 10
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado(null);
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 10: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 11
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("OTRO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 11: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 12
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida(null);
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 12: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }
        //Caso 13
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("202/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 13: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 14
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("1111/11/111");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 14: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 15
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/01");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 15: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 16
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2023/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 16: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 17
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida(null);
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 17: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 18
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:0");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 18: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 19
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:111");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 19: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 20
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("19:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 20: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 21
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(-1.5);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 21: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 22
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(null);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 22: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 24
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        pedido.setId(999);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 24: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 26
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("PREPARADO");
        pedido.setFechaRecogida("2024/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        pedido.setId(-1);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTests", "Prueba 26: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Eliminar el pedido creado para el test
        pedido.setId(id);
        mPedidoRepository.delete(pedido);
    }

    private void deleteTestPedido() {
        //Creacion del plato a eliminar
        Pedido pedido = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2024/01/02", "20:00",
                20.0);
        pedido.setId((int) mPedidoRepository.insert(pedido));

        //Caso 1
        try {
            mPedidoRepository.delete(pedido);
            Log.d("UnitTests", "Prueba 1: Delete correcto." +
                    "\nHa dado un valor correcto, como se esperaba");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        //Caso 2
        try {
            pedido.setId(12345);
            mPedidoRepository.delete(pedido);
            Log.d("UnitTests", "Prueba 2: Delete incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        //Caso 4
        try {
            pedido.setId(-1);
            mPedidoRepository.delete(pedido);
            Log.d("UnitTests", "Prueba 3: Delete incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }
    }


    private void runTestPlato(){
        mPlatoRepository = new PlatoRepository(mContext);
        Log.i("UnitTest", "Comenzando test de insert platos...");
        insertTestPlato();
        Log.i("UnitTest", "Comenzando test de update platos...");
        updateTestPlato();
        Log.i("UnitTest", "Comenzando test de delete platos...");
        deleteTestPlato();
    }

    private void runTestPedido(){
        mPedidoRepository = new PedidoRepository(mContext);
        Log.i("UnitTest", "Comenzando test de insert pedidos...");
        insertTestPedido();
        Log.i("UnitTest", "Comenzando test de update pedidos...");
        updateTestPedido();
        Log.i("UnitTest", "Comenzando test de delete pedidos...");
        deleteTestPedido();
    }

    private void runTestVolumenPlatos(){
        mPlatoRepository = new PlatoRepository(mContext);

        String prefijoPlato = "plato_";
        int cantidadPlatos = 100;
        List<Plato> listaPlatos = new ArrayList<>();
        // Añadir platos
        for (int i = 1; i <= cantidadPlatos; i++) {
            String nombrePlato = prefijoPlato + i;
            Plato plato = new Plato(nombrePlato, "Descripción", "CATEGORIA", 10.0); // Ajusta la creación del plato según tu implementación
            try {
                int idPlato = (int) mPlatoRepository.insert(plato);
                plato.setId(idPlato);
                listaPlatos.add(plato);
            } catch (Throwable throwable) {
                // Manejar excepción específica o registrar el error según tus necesidades
                Log.d("UnitTest", i+": " + throwable.getMessage());
            }
        }
        for(Plato plato : listaPlatos){
            mPlatoRepository.delete(plato);
        }

    }


    public void runAllTests(){
        //runTestPlato();
        //runTestPedido();
    }

    public void runTestVolumen(){
        runTestVolumenPlatos();
    }

}
