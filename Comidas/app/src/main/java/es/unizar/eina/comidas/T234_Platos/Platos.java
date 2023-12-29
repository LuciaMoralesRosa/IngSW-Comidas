package es.unizar.eina.comidas.T234_Platos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.T234_Comidas.R;

/**
 * Pantalla principal de la aplicación Comidas.
 * Muestra una lista de platos y proporciona opciones para agregar, editar y eliminar platos.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
public class Platos extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /** ViewModel para gestionar los datos relacionados con los platos. */
    private PlatoViewModel mPlatoViewModel;
    /** Código de solicitud para la creación de un nuevo plato. */
    public static final int ACTIVITY_CREATE = 1;
    /** Código de solicitud para la edición de un plato existente. */
    public static final int ACTIVITY_EDIT = 2;
    /** Identificador del elemento de menú para agregar un nuevo plato. */
    static final int INSERT_ID = Menu.FIRST;
    /** Identificador del elemento de menú para eliminar un plato. */
    static final int DELETE_ID = Menu.FIRST + 1;
    /** Identificador del elemento de menú para editar un plato. */
    static final int EDIT_ID = Menu.FIRST + 2;

    /** Vista de reciclaje para mostrar la lista de platos. */
    RecyclerView mRecyclerView;
    /** Adaptador para la lista de platos. */
    PlatoListAdapter mAdapter;
    /** Botón flotante para agregar un nuevo plato. */
    FloatingActionButton mFab;

    Integer numeroDePlatos;
    List<String> nombresDePlatos;

    /**
     * Se llama cuando la actividad se está iniciando. Aquí se realiza la inicialización de la
     * interfaz de usuario, se configuran los listeners y se vinculan los datos con el ViewModel.
     *
     * @param savedInstanceState La instancia anteriormente guardada del estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        mPlatoViewModel.getAllPlatos().observe(this, platos -> {
            numeroDePlatos = 0;
            nombresDePlatos = new ArrayList<>();
            for(Plato plato : platos) {
                numeroDePlatos++;
                nombresDePlatos.add(plato.getNombre());
            }
            // Actualiza la copia en caché de los platos en el adaptador.
            mAdapter.submitList(platos);
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            createPlato();
        });

        Spinner spinner =findViewById(R.id.spinnerOrdenar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.ordenarPlatos,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // It doesn't affect if we comment the following instruction
        registerForContextMenu(mRecyclerView);

    }

    /**
     * Maneja los eventos de selección de elementos del menú.
     *
     * @param item El elemento del menú seleccionado.
     * @return True si se maneja el evento, false de lo contrario.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case INSERT_ID:
                createPlato();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Maneja el resultado de las actividades secundarias, como la creación o edición de un plato.
     *
     * @param requestCode El código de solicitud original enviado a la actividad.
     * @param resultCode El código de resultado devuelto por la actividad secundaria.
     * @param data Los datos adicionales devueltos por la actividad secundaria.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();

        if (resultCode != RESULT_OK) {
            switch (resultCode){
                case 2:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.precio_not_saved_platos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.categoria_not_saved_platos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.unicidad_not_saved_platos,
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved_platos,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        } else {
            switch (requestCode) {
                case ACTIVITY_CREATE: //Creacion de un plato
                    String nombreDelPlato = extras.getString(PlatoEdit.PLATO_NOMBRE);
                    if(numeroDePlatos == 100){ //Comprobar numero maximo de platos
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.not_saved_full_platos,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Plato newPlato = new Plato(nombreDelPlato
                                , extras.getString(PlatoEdit.PLATO_DESCRIPCION)
                                , extras.getString(PlatoEdit.PLATO_CATEGORIA)
                                , extras.getDouble(PlatoEdit.PLATO_PRECIO));
                        mPlatoViewModel.insert(newPlato);
                    }
                    break;
                case ACTIVITY_EDIT: //Edicion de un plato
                    int platoId = extras.getInt(PlatoEdit.PLATO_ID);
                    Plato updatedPlato = new Plato(extras.getString(PlatoEdit.PLATO_NOMBRE)
                            , extras.getString(PlatoEdit.PLATO_DESCRIPCION)
                            , extras.getString(PlatoEdit.PLATO_CATEGORIA)
                            , extras.getDouble(PlatoEdit.PLATO_PRECIO));
                    updatedPlato.setPlatoId(platoId);
                    mPlatoViewModel.update(updatedPlato);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Maneja la selección de elementos de contexto, como editar o eliminar un plato.
     *
     * @param item El elemento de menú de contexto seleccionado.
     * @return True si se maneja el evento, false de lo contrario.
     */
    public boolean onContextItemSelected(MenuItem item) {
        Plato current = mAdapter.getCurrent();
        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(
                        getApplicationContext(),
                        "Deleting " + current.getNombre(),
                        Toast.LENGTH_LONG).show();
                mPlatoViewModel.delete(current);
                return true;
            case EDIT_ID:
                editPlato(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Inicia la actividad para crear un nuevo plato.
     */
    private void createPlato() {
        Intent intent = new Intent(this, PlatoEdit.class);

        //Pasar la lista de nombres de platos a la nueva actividad
        Bundle bundle = new Bundle();
        ArrayList<String> listaNombres = new ArrayList<String>(nombresDePlatos);
        bundle.putSerializable("listaNombresPlatos", listaNombres);
        intent.putExtras(bundle);
        intent.putExtra("listaNombres", bundle);
        startActivityForResult(intent, ACTIVITY_CREATE);
    }

    /**
     * Inicia la actividad para editar el plato seleccionado.
     *
     * @param current El plato que se va a editar.
     */
    private void editPlato(Plato current) {
        Intent intent = new Intent(this, PlatoEdit.class);

        //Pasar los valores del plato actual a la nueva actividad
        intent.putExtra(PlatoEdit.PLATO_NOMBRE, current.getNombre());
        intent.putExtra(PlatoEdit.PLATO_DESCRIPCION, current.getDescripcion());
        intent.putExtra(PlatoEdit.PLATO_CATEGORIA, current.getCategoria());
        intent.putExtra(PlatoEdit.PLATO_PRECIO, current.getPrecio());
        intent.putExtra(PlatoEdit.PLATO_ID, current.getPlatoId());

        //Pasar la lista de nombres de platos a la nueva actividad
        Bundle bundle = new Bundle();
        ArrayList<String> listaNombres = new ArrayList<String>(nombresDePlatos);
        bundle.putSerializable("listaNombresPlatos", listaNombres);
        intent.putExtras(bundle);
        intent.putExtra("listaNombres", bundle);
        startActivityForResult(intent, ACTIVITY_EDIT);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos().observe(this, platos -> {
            List<Plato> listaPlatos = new ArrayList<>(platos);
            String itemSelected = parent.getItemAtPosition(position).toString();

            //Orden de las categorias
            Map<String, Integer> categoriasOrden = new HashMap<>();
            categoriasOrden.put("PRIMERO", 1);
            categoriasOrden.put("SEGUNDO", 2);
            categoriasOrden.put("POSTRE", 3);

            switch (itemSelected) {
                case "Ordenar por nombre":
                    listaPlatos.sort(Comparator.comparing(Plato::getNombre,
                                     String.CASE_INSENSITIVE_ORDER));
                    break;
                case "Ordenar por categoria":
                    listaPlatos.sort(Comparator.comparing(plato -> categoriasOrden.get(plato.getCategoria())));
                    break;
                case "Ordenar por nombre y categoria":
                    listaPlatos.sort(Comparator.comparing(Plato::getCategoria).
                            thenComparing(Plato::getNombre, String.CASE_INSENSITIVE_ORDER));
                    break;
            }
            mAdapter.submitList(listaPlatos);
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}