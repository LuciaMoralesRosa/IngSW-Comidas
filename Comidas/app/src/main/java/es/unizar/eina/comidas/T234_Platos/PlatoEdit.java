package es.unizar.eina.comidas.T234_Platos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import es.unizar.eina.T234_Comidas.R;
//import es.unizar.eina.comidas.R;

/**
 * Pantalla utilizada para la creación o edición de un plato
 * Permite al usuario ingresar o editar información relacionada con un plato, como nombre,
 * descripción, categoría y precio.
 *
 * @author Lucia Morales
 * @author Curro Valero
 * */
public class PlatoEdit extends AppCompatActivity {

    /** Nombre del plato*/
    public static final String PLATO_NOMBRE = "nombrePlato";
    /** Descripcion del plato*/
    public static final String PLATO_DESCRIPCION = "descripcionPlato";
    /** Categoria del plato*/
    public static final String PLATO_CATEGORIA = "categoriaPlato";
    /** Precio del plato*/
    public static final String PLATO_PRECIO = "precioPlato";
    /** Identificador del plato*/
    public static final String PLATO_ID = "id";

    /** Campo de entrada para el nombre del plato */
    private EditText mNombreText;

    /** Campo de entrada para la descripción del plato */
    private EditText mDescripcionText;

    /** Campo de entrada para la categoría del plato. */
    private EditText mCategoriaText;

    /** Campo de entrada para el precio del plato. */
    private EditText mPrecioText;

    /** Identificador del plato. */
    private Integer mRowId;

    /** Botón para guardar o actualizar la información del plato. */
    Button mSaveButton;

    PlatoViewModel mPlatoViewModel;
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
        setContentView(R.layout.activity_platoedit);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos();

        mNombreText = findViewById(R.id.nombrePlato);
        mDescripcionText = findViewById(R.id.descripcionPlato);
        mCategoriaText = findViewById(R.id.categoriaPlato);
        mPrecioText = findViewById(R.id.precioPlato);

        mSaveButton = findViewById(R.id.button_platos);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mNombreText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(PlatoEdit.PLATO_NOMBRE, mNombreText.getText().toString());
                replyIntent.putExtra(PlatoEdit.PLATO_DESCRIPCION,
                                     mDescripcionText.getText().toString());
                replyIntent.putExtra(PlatoEdit.PLATO_CATEGORIA,
                                     mCategoriaText.getText().toString());
                replyIntent.putExtra(PlatoEdit.PLATO_PRECIO, mPrecioText.getText());
                if (mRowId != null) {
                    replyIntent.putExtra(PlatoEdit.PLATO_ID, mRowId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        populateFields();

    }

    /**
     * Rellena los campos de entrada con los valores proporcionados a través de los extras del
     * intent.
     * Si no hay extras, los campos permanecerán vacíos.
     */
    private void populateFields () {
        mRowId = null;
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            mNombreText.setText(extras.getString(PlatoEdit.PLATO_NOMBRE));
            mDescripcionText.setText(extras.getString(PlatoEdit.PLATO_DESCRIPCION));
            mCategoriaText.setText(extras.getString(PlatoEdit.PLATO_CATEGORIA));
            mPrecioText.setText(extras.getString(PlatoEdit.PLATO_PRECIO));
            mRowId = extras.getInt(PlatoEdit.PLATO_ID);
        }
    }

}
