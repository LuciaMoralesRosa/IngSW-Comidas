package es.unizar.eina.comidas.T234_Platos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.unizar.eina.comidas.T234_Pedidos.*;

import es.unizar.eina.T234_Comidas.R;
import es.unizar.eina.comidas.database.PlatoRepository;
//import es.unizar.eina.comidas.R;

/**
 * Pantalla utilizada para la creación o edición de un plato
 * Permite al usuario ingresar o editar información relacionada con un plato, como nombre,
 * descripción, categoría y precio.
 *
 * @author Lucia Morales
 * @author Curro Valero
 * */
public class InicioApp extends AppCompatActivity {

    /** Botón para navegar a platos. */
    Button mIrPlatos;
    /** Botón para navegar a pedidos. */
    Button mIrPedidos;

    Button mPruebasUnitarias;
    Button mPruebasVolumen;

    Context mContext;

    /**
     * Se llama cuando la actividad se está iniciando. Aquí se realiza la inicialización de la
     * interfaz de usuario, se configuran los listeners y se recuperan los datos pasados como
     * extras en el intent.
     *
     * @param savedInstanceState La instancia anteriormente guardada del estado de la actividad.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mContext = this;

        mIrPlatos = findViewById(R.id.button_platos);
        mIrPedidos = findViewById(R.id.button_pedidos);
        mPruebasUnitarias = findViewById(R.id.button_pruebasUnitarias);
        mPruebasVolumen = findViewById(R.id.button_pruebasVolumen);

        mIrPlatos.setOnClickListener(view -> {
            Intent intent = new Intent(this, Platos.class);
            startActivity(intent);
        });
        mIrPedidos.setOnClickListener(view -> {
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
        });

        mPruebasUnitarias.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Se estan ejecutando las pruebas unitarias..." ,
                    Toast.LENGTH_LONG).show();
            Toast.makeText(
                    getApplicationContext(),
                    "Espere hasta que se le notifique la finalizacion del proceso" ,
                    Toast.LENGTH_LONG).show();
            runTestUnitarios();
            Toast.makeText(
                    getApplicationContext(),
                    "Se ha terminado de ejecutar las pruebas con exito" ,
                    Toast.LENGTH_LONG).show();
        });

        mPruebasVolumen.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Se estan ejecutando las pruebas de volumen..." ,
                    Toast.LENGTH_LONG).show();
            Toast.makeText(
                    getApplicationContext(),
                    "Espere hasta que se le notifique la finalizacion del proceso" ,
                    Toast.LENGTH_LONG).show();
            runTestVolumen();
            Toast.makeText(
                    getApplicationContext(),
                    "Se ha terminado de ejecutar las pruebas con exito" ,
                    Toast.LENGTH_LONG).show();
        });
    }

    private void runTestUnitarios(){
        UnitTest unitTests = new UnitTest(mContext);
        unitTests.runAllTests();
    }

    private void runTestVolumen(){
        UnitTest unitTests = new UnitTest(mContext);
        unitTests.runTestVolumen();
    }

}
