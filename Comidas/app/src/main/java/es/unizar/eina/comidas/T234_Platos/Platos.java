package es.unizar.eina.comidas.T234_Platos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.T234_Comidas.R;

/**
 * Pantalla principal de la aplicación Comidas.
 * Muestra una lista de platos y proporciona opciones para agregar, editar y eliminar platos.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
public class Platos extends AppCompatActivity {

    /** ViewModel para gestionar los datos relacionados con los platos. */
    private PlatoViewModel mPlatoViewModel;
    /** Código de solicitud para la creación de un nuevo plato. */
    public static final int ACTIVITY_CREATE = 1;
    /** Código de solicitud para la edición de un plato existente. */
    public static final int ACTIVITY_EDIT = 2;
    /** Código de solicitud para la ordenacion de los platos existentes. */
    public static final int ACTIVITY_SORT = 3;
    /** Identificador del elemento de menú para agregar un nuevo plato. */
    static final int INSERT_ID = Menu.FIRST;
    /** Identificador del elemento de menú para eliminar un plato. */
    static final int DELETE_ID = Menu.FIRST + 1;
    /** Identificador del elemento de menú para editar un plato. */
    static final int EDIT_ID = Menu.FIRST + 2;

    static final int ORDER_PLATOS_BY_NAME = Menu.FIRST + 3;
    static final int ORDER_PLATOS_BY_CATEGORY = Menu.FIRST + 4;
    static final int ORDER_PLATOS = Menu.FIRST + 5;

    /** Vista de reciclaje para mostrar la lista de platos. */
    RecyclerView mRecyclerView;
    /** Adaptador para la lista de platos. */
    PlatoListAdapter mAdapter;
    /** Botón flotante para agregar un nuevo plato. */
    FloatingActionButton mFab;

    /** Botón flotante para ordenar los platos*/
    FloatingActionButton mOrdenar;

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
            // Actualiza la copia en caché de los platos en el adaptador.
            mAdapter.submitList(platos);
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            createPlato();
        });

        // It doesn't affect if we comment the following instruction
        registerForContextMenu(mRecyclerView);

    }

    /**
     * Crea el menú de opciones de la actividad.
     *
     * @param menu El menú en el que se colocarán las opciones.
     * @return True para mostrar el menú, false de lo contrario.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, ORDER_PLATOS_BY_NAME, Menu.NONE, R.string.sort_platos_by_name);
        menu.add(Menu.NONE, ORDER_PLATOS_BY_CATEGORY, Menu.NONE, R.string.sort_platos_by_category);
        menu.add(Menu.NONE, ORDER_PLATOS, Menu.NONE, R.string.sort_platos);
        return result;
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
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            switch (requestCode) {
                case ACTIVITY_CREATE: //Creacion de un plato
                    Plato newPlato = new Plato(extras.getString(PlatoEdit.PLATO_NOMBRE)
                            , extras.getString(PlatoEdit.PLATO_DESCRIPCION)
                            , extras.getString(PlatoEdit.PLATO_CATEGORIA)
                            , extras.getDouble(PlatoEdit.PLATO_PRECIO));
                    mPlatoViewModel.insert(newPlato);
                    break;
                case ACTIVITY_EDIT: //Edicion de un plato
                    int id = extras.getInt(PlatoEdit.PLATO_ID);
                    Plato updatedPlato = new Plato(extras.getString(PlatoEdit.PLATO_NOMBRE)
                            , extras.getString(PlatoEdit.PLATO_DESCRIPCION)
                            , extras.getString(PlatoEdit.PLATO_CATEGORIA)
                            , extras.getDouble(PlatoEdit.PLATO_PRECIO));
                    updatedPlato.setId(id);
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
            case ORDER_PLATOS_BY_NAME:
                mPlatoViewModel.getAllPlatos();
                sortPlato();
                return true;
            case ORDER_PLATOS_BY_CATEGORY:
                mPlatoViewModel.getAllPlatos();
                sortPlato();
                return true;
            case ORDER_PLATOS:
                mPlatoViewModel.getAllPlatos();
                sortPlato();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Inicia la actividad para crear un nuevo plato.
     */
    private void createPlato() {
        Intent intent = new Intent(this, PlatoEdit.class);
        startActivityForResult(intent, ACTIVITY_CREATE);
    }

    private void sortPlato(){
        Intent intent = new Intent(this, Platos.class);
        startActivityForResult(intent, ACTIVITY_SORT);
    }

    /**
     * Inicia la actividad para editar el plato seleccionado.
     *
     * @param current El plato que se va a editar.
     */
    private void editPlato(Plato current) {
        Intent intent = new Intent(this, PlatoEdit.class);
        intent.putExtra(PlatoEdit.PLATO_NOMBRE, current.getNombre());
        intent.putExtra(PlatoEdit.PLATO_DESCRIPCION, current.getDescripcion());
        intent.putExtra(PlatoEdit.PLATO_CATEGORIA, current.getCategoria());
        intent.putExtra(PlatoEdit.PLATO_PRECIO, current.getPrecio());
        intent.putExtra(PlatoEdit.PLATO_ID, current.getId());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

}