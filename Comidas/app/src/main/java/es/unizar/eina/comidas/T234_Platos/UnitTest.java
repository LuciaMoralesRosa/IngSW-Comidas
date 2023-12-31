package es.unizar.eina.comidas.T234_Platos;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import org.junit.Test;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
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
    List<Plato> listaPlatos;
    List<Pedido> listaPedidos;

    private final int cantidadPlatos = 101;
    private final int cantidadPedidos = 2000;
    private int TestIncorrectos;
    private int TestCorrectos;

    public UnitTest(Context context){
        super();
        mContext = context;
        mPedidoRepository = new PedidoRepository(mContext);
        mPlatoRepository = new PlatoRepository(mContext);
        listaPlatos = new ArrayList<>();
        listaPedidos = new ArrayList<>();
        TestIncorrectos = 0;
        TestCorrectos = 0;
    }

    private void showLogTrowableValorNoEsperado(Throwable throwable){
        Log.d("UnitTest", "Error en la ejecución de la prueba: " + throwable.getMessage()
                + "\nHa dado un valor no esperado");
    }

    private void showLogTrowableValorEsperado(Throwable throwable){
        Log.d("UnitTest", "Error en la ejecución de la prueba: " + throwable.getMessage()
                + "\nHa dado un valor incorrecto, como se esperaba");
        TestIncorrectos++;
    }

    private void mensajeFinalTest(int testCorrectosExpected, int testIncorrectosExpected) {
        Log.i("UnitTest", "\nResultados del test:\n" +
                "\tSe han obtenido: " + TestCorrectos + " test correctos \n" +
                "\tSe han obtenido: " + TestIncorrectos + " test incorrectos");
        try {
            assertEquals(testCorrectosExpected, TestCorrectos);
            Log.i("UnitTest", "El numero de test correctos esperados y reales es igual");
        } catch (Throwable throwable) {
            Log.i("UnitTest", "ERROR: El numero de test correctos esperados y reales es distinto");
        }
        try {
            assertEquals(testIncorrectosExpected, TestIncorrectos);
            Log.i("UnitTest", "El numero de test incorrectos esperados y reales es igual");
        } catch (Throwable throwable){
            Log.i("UnitTest", "ERROR: El numero de test incorrectos esperados y reales es distinto\n");
        }
        TestCorrectos = 0;
        TestIncorrectos = 0;
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
            Log.d("UnitTest", "Prueba 1: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion \"\", categoria = \"PRIMERO\", precio = 12.5");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 2: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion = \"Desc\", categoria = \"SEGUNDO\", precio = 0.0");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 3: Insert correcto - nombre = \"Plato de prueba\"" +
                    ", descripcion = \"Desc\", categoria = \"POSTRE\", precio = 12.5");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 4: Insert incorrecto - nombre = \"\", descripcion" +
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
            Log.d("UnitTest", "Prueba 5: Insert incorrecto - nombre = null, descripcion" +
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
            Log.d("UnitTest", "Prueba 6 : Insert incorrecto - nombre = \"Plato de" +
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
            Log.d("UnitTest", "Prueba 7 : Insert incorrecto - nombre = \"Plato de " +
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
            Log.d("UnitTest", "Prueba 8 : Insert incorrecto - nombre = \"Plato de" +
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
            Log.d("UnitTest", "Prueba 9 : Insert incorrecto - nombre = \"Plato de" +
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
            Log.d("UnitTest", "Prueba 10 : Insert incorrecto - nombre = \"Plato de" +
                    " prueba\", descripcion = \"Desc\", categoria = \"POSTRE\", precio = null");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(3, 7);

        //Eliminacion de platos creados para la prueba
        for(Plato plato : platosDePrueba){
            mPlatoRepository.delete(plato);
        }
        Log.i("UnitTest", "Se han eliminado los platos generados en la prueba de INSERT");
    }

    @Test
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
            Log.d("UnitTest", "Prueba 1: Update correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 2: Update correcto");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 3: Update correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
            Log.d("UnitTest", "Prueba 4: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 5: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 6: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 7: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 8: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 9: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 10: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 11: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 12: Update incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(3, 9);

        //Eliminacion del plato creado para la prueba
        plato.setId(id);
        mPlatoRepository.delete(plato);
        Log.d("UnitTest", "Se han eliminado los platos generados en la prueba");
    }

    @Test
    private void deleteTestPlato() {
        //Creacion del plato a eliminar
        Plato plato = new Plato("Plato a eliminar", "",
                "PRIMERO", 12.5);
        plato.setId((int) mPlatoRepository.insert(plato));

        // Casos de prueba para las clases de equivalencia validas

        //Caso 1
        try {
            mPlatoRepository.delete(plato);
            Log.d("UnitTest", "Prueba 1: Delete correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Casos de prueba para las clases de equivalencia no validas

        //Caso 2
        try {
            plato.setId(12345);
            mPlatoRepository.delete(plato);
            Log.d("UnitTest", "Prueba 2: Delete incorrecto.");
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
            Log.d("UnitTest", "Prueba 3: Delete incorrecto.");
            if (mPlatoRepository.getException() != null) {
                throw mPlatoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(1, 2);
    }



    @Test
    private void insertTestPedido(){
        ArrayList<Pedido> pedidosDePrueba = new ArrayList<>();

        // Casos de prueba para las clases de equivalencia validas

        // Caso 1 : Insert correcto
        Pedido prueba1 = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba1);
        try {
            prueba1.setId((int) mPedidoRepository.insert(prueba1));
            Log.d("UnitTest", "Prueba 1: Insert correcto");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 2 : Insert correcto
        Pedido prueba2 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "20:00",
                0.0);
        pedidosDePrueba.add(prueba2);
        try {
            prueba2.setId((int) mPedidoRepository.insert(prueba2));
            Log.d("UnitTest", "Prueba 2: Insert correcto");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Caso 3 : Insert correcto
        Pedido prueba3 = new Pedido("Nombre", 123456789,
                "RECOGIDO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba3);
        try {
            prueba3.setId((int) mPedidoRepository.insert(prueba3));
            Log.d("UnitTest", "Prueba 3: Insert correcto");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Casos de prueba para las clases de equivalencia no validas

        // Caso 4 : Insert incorrecto
        Pedido prueba4 = new Pedido("", 123456789,
                "PREPARADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba4);
        try {
            prueba4.setId((int) mPedidoRepository.insert(prueba4));
            Log.d("UnitTest", "Prueba 4: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 5 : Insert incorrecto
        Pedido prueba5 = new Pedido(null, 123456789,
                "PREPARADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba5);
        try {
            prueba5.setId((int) mPedidoRepository.insert(prueba5));
            Log.d("UnitTest", "Prueba 5: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 6 : Insert incorrecto
        Pedido prueba6 = new Pedido("Nombre", null,
                "PREPARADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba6);
        try {
            prueba6.setId((int) mPedidoRepository.insert(prueba6));
            Log.d("UnitTest", "Prueba 6: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 7 : Insert incorrecto
        Pedido prueba7 = new Pedido("Nombre", 12345678,
                "PREPARADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba7);
        try {
            prueba7.setId((int) mPedidoRepository.insert(prueba7));
            Log.d("UnitTest", "Prueba 7: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 8 : Insert incorrecto
        Pedido prueba8 = new Pedido("Nombre", 1111111111,
                "PREPARADO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba8);
        try {
            prueba8.setId((int) mPedidoRepository.insert(prueba8));
            Log.d("UnitTest", "Prueba 8: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 10 : Insert incorrecto
        Pedido prueba10 = new Pedido("Nombre", 123456789,
                null, "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba10);
        try {
            prueba10.setId((int) mPedidoRepository.insert(prueba10));
            Log.d("UnitTest", "Prueba 10: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 11 : Insert incorrecto
        Pedido prueba11 = new Pedido("Nombre", 123456789,
                "OTRO", "2025/01/02", "20:00",
                20.0);
        pedidosDePrueba.add(prueba11);
        try {
            prueba11.setId((int) mPedidoRepository.insert(prueba11));
            Log.d("UnitTest", "Prueba 11: Insert incorrecto");
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
            Log.d("UnitTest", "Prueba 12: Insert incorrecto");
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
            Log.d("UnitTest", "Prueba 13: Insert incorrecto");
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
            Log.d("UnitTest", "Prueba 14: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 15 : Insert incorrecto
        Pedido prueba15 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/06", "20:00",
                20.0);
        pedidosDePrueba.add(prueba15);
        try {
            prueba15.setId((int) mPedidoRepository.insert(prueba15));
            Log.d("UnitTest", "Prueba 15: Insert incorrecto");
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
            Log.d("UnitTest", "Prueba 16: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 17 : Insert incorrecto
        Pedido prueba17 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", null,
                20.0);
        pedidosDePrueba.add(prueba17);
        try {
            prueba17.setId((int) mPedidoRepository.insert(prueba17));
            Log.d("UnitTest", "Prueba 17: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 18 : Insert incorrecto
        Pedido prueba18 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "20:0",
                20.0);
        pedidosDePrueba.add(prueba18);
        try {
            prueba18.setId((int) mPedidoRepository.insert(prueba18));
            Log.d("UnitTest", "Prueba 18: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 19 : Insert incorrecto
        Pedido prueba19 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "20:111",
                20.0);
        pedidosDePrueba.add(prueba19);
        try {
            prueba19.setId((int) mPedidoRepository.insert(prueba19));
            Log.d("UnitTest", "Prueba 19: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 20 : Insert incorrecto
        Pedido prueba20 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "19:00",
                20.0);
        pedidosDePrueba.add(prueba20);
        try {
            prueba20.setId((int) mPedidoRepository.insert(prueba20));
            Log.d("UnitTest", "Prueba 20: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 21 : Insert incorrecto
        Pedido prueba21 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "20:00",
                -1.5);
        pedidosDePrueba.add(prueba21);
        try {
            prueba21.setId((int) mPedidoRepository.insert(prueba21));
            Log.d("UnitTest", "Prueba 21: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        // Caso 22 : Insert incorrecto
        Pedido prueba22 = new Pedido("Nombre", 123456789,
                "PREPARADO", "2025/01/02", "20:00",
                null);
        pedidosDePrueba.add(prueba22);
        try {
            prueba22.setId((int) mPedidoRepository.insert(prueba22));
            Log.d("UnitTest", "Prueba 22: Insert incorrecto");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(3, 18);

        //Eliminacion de pedidos de prueba
        for(Pedido pedido : pedidosDePrueba){
            mPedidoRepository.delete(pedido);
        }
    }

    @Test
    private void updateTestPedido(){
        //Creacion del pedido a modificar
        Pedido pedido = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2025/01/02", "20:00",
                20.0);
        int id = (int) mPedidoRepository.insert(pedido);
        pedido.setId(id);

        // Casos de prueba para las clases de equivalencia validas

        //Caso 1
        pedido.setNombreCliente("Nom");
        pedido.setTelefonoCliente(123456789);
        pedido.setEstado("SOLICITADO");
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 1: Update correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(0.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 2: Update correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 3: Update correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 4: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 5: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 6: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 7: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 8: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 10: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 11: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 12: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 13: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 14: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/01");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 15: Update incorrecto.");
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
            Log.d("UnitTest", "Prueba 16: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida(null);
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 17: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:0");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 18: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:111");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 19: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("19:00");
        pedido.setPrecioPedido(20.0);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 20: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(-1.5);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 21: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(null);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 22: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        pedido.setId(999);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 24: Update incorrecto.");
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
        pedido.setFechaRecogida("2025/01/02");
        pedido.setHoraRecogida("20:00");
        pedido.setPrecioPedido(20.0);
        pedido.setId(-1);
        try {
            mPedidoRepository.update(pedido);
            Log.d("UnitTest", "Prueba 26: Update incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(3, 20);

        //Eliminar el pedido creado para el test
        pedido.setId(id);
        mPedidoRepository.delete(pedido);
    }

    @Test
    private void deleteTestPedido() {
        //Creacion del plato a eliminar
        Pedido pedido = new Pedido("Nombre", 123456789,
                "SOLICITADO", "2025/01/02", "20:00",
                20.0);
        pedido.setId((int) mPedidoRepository.insert(pedido));

        // Casos de prueba para las clases de equivalencia validas

        //Caso 1
        try {
            mPedidoRepository.delete(pedido);
            Log.d("UnitTest", "Prueba 1: Delete correcto.");
            Log.d("UnitTest", "\nHa dado un valor correcto, como se esperaba");
            TestCorrectos++;
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorNoEsperado(throwable);
        }

        // Casos de prueba para las clases de equivalencia no validas

        //Caso 2
        try {
            pedido.setId(12345);
            mPedidoRepository.delete(pedido);
            Log.d("UnitTest", "Prueba 2: Delete incorrecto.");
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
            Log.d("UnitTest", "Prueba 3: Delete incorrecto.");
            if (mPedidoRepository.getException() != null) {
                throw mPedidoRepository.getException();
            }
        } catch (Throwable throwable) {
            // Capturar cualquier excepción que se haya lanzado
            showLogTrowableValorEsperado(throwable);
        }

        mensajeFinalTest(1, 2);

    }


    private void runTestPlato(){
        Log.i("UnitTest", "Comenzando test de insert platos...");
        insertTestPlato();
        Log.i("UnitTest", "Comenzando test de update platos...");
        updateTestPlato();
        Log.i("UnitTest", "Comenzando test de delete platos...");
        deleteTestPlato();
    }

    private void runTestPedido(){
        Log.i("UnitTest", "Comenzando test de insert pedidos...");
        insertTestPedido();
        Log.i("UnitTest", "Comenzando test de update pedidos...");
        updateTestPedido();
        Log.i("UnitTest", "Comenzando test de delete pedidos...");
        deleteTestPedido();
    }

    private void runTestVolumenPlatos(){
        String prefijoPlato = "Plato_Volumen_";
        // Añadir platos
        for (int i = 1; i <= cantidadPlatos; i++) {
            String nombrePlato = prefijoPlato;
            if(i < 10) {
                nombrePlato = nombrePlato + "00" + i;
            } else if(i < 100) {
                nombrePlato = nombrePlato + "0" + i;
            } else{
                nombrePlato = nombrePlato + i;
            }
            Plato plato = new Plato(nombrePlato, "Descripción", "PRIMERO", 10.0);
            try {
                int idPlato = (int) mPlatoRepository.insert(plato);
                plato.setId(idPlato);
                listaPlatos.add(plato);
            } catch (Throwable throwable) {
                // Manejar excepción específica o registrar el error según tus necesidades
                Log.d("UnitTest", i+": " + throwable.getMessage());
            }
        }

    }

    private void runTestVolumenPedidos(){
        String prefijoPedido = "Pedido_Volumen_";
        // Añadir platos
        for (int i = 1; i <= cantidadPedidos; i++) {
            String nombreCliente = prefijoPedido;
            if(i < 10) {
                nombreCliente = nombreCliente + "000" + i;
            } else if(i < 100) {
                nombreCliente = nombreCliente + "00" + i;
            } else if (1 < 1000){
                nombreCliente = nombreCliente + "0" + i;
            }else{
                nombreCliente = nombreCliente + i;
            }

            Pedido pedido = new Pedido(nombreCliente, 123456789,
                    "SOLICITADO", "2025/01/02", "20:00",
                    20.0);
            try {
                int idPedido = (int) mPedidoRepository.insert(pedido);
                pedido.setId(idPedido);
                listaPedidos.add(pedido);
            } catch (Throwable throwable) {
                // Manejar excepción específica o registrar el error según tus necesidades
                Log.d("UnitTest", i+": " + throwable.getMessage());
            }
        }

    }

    public void borrarObjetosVolumen(){
        if(listaPlatos != null){
            for(Plato plato : listaPlatos){
                mPlatoRepository.delete(plato);
            }
        }
        Log.i("UnitTest - Borrado", "Se han borrado: " +
                listaPlatos.size() + " platos");
        listaPlatos.clear();

        if(listaPedidos != null){
            for(Pedido pedido : listaPedidos){
                mPedidoRepository.delete(pedido);
            }
        }
        Log.i("UnitTest - Borrado", "Se han borrado: " +
                listaPedidos.size() + " pedidos");
        listaPedidos.clear();
    }

    public void runAllTestsUnitarios(){
        runTestPlato();
        runTestPedido();
    }

    public void runTestVolumen(){
        runTestVolumenPlatos();
        runTestVolumenPedidos();
        Log.i("UnitTest - Volumen", "Se han añadido " + cantidadPlatos +
                " platos y " + cantidadPedidos + " pedidos");
        Log.i("UnitTest - Volumen", "Ahora hay " + listaPlatos.size() +
                " platos y " + listaPedidos.size() + " pedidos generados por " +
                "las pruebas de volumen");
    }

    public void runTestSobrecarga() {
        String prefijoPlato = "Plato_Sobrecarga_";
        String descripcion = "Descripcion_";
        Boolean continuar = true;

        //inicializarStringConCeros
        int tamDelString = 10000;
        char[] ceros = new char[tamDelString];
        Arrays.fill(ceros, '0');
        String cadenaDeCeros = new String(ceros);

        int i = 0;
        try {
            while (continuar) {
                String nombrePlato = prefijoPlato;
                if (i < 10) {
                    nombrePlato = nombrePlato + "00" + i;
                } else if (i < 100) {
                    nombrePlato = nombrePlato + "0" + i;
                } else {
                    nombrePlato = nombrePlato + i;
                }
                try {
                    descripcion = descripcion + cadenaDeCeros;
                    Plato plato = new Plato(nombrePlato, descripcion, "PRIMERO", 10.0);
                    int idPlato = (int) mPlatoRepository.insert(plato);
                    plato.setId(idPlato);
                    listaPlatos.add(plato);
                } catch (Throwable throwable) {
                    Log.d("UnitTest - SobreCarga", "No se ha podido introducir el plato.\n" +
                            throwable.getMessage());
                    Log.i("UnitTest - Sobrecarga", "Se han introducido " + (i - 1) +
                            " platos correctamente antes del error");
                    Log.i("UnitTest - Sobrecarga", "El plato " + i + " contenia una" +
                            " descripcion con " + descripcion.length() + " caracteres");
                    continuar = false;
                }
                if (i % 100 == 0) {
                    Log.d("UnitTest - Sobrecarga", "\tSe han introducido " + i + " platos \n" +
                            "\tEl plato " + i + " tiene una descripcion de " + descripcion.length() +
                            " caracteres");
                }
                i++;
            }
        } catch (OutOfMemoryError e){
            Log.d("UnitTest - Sobrecarga", "Se ha capturado un error outOfMemory");
        }
    }
}
