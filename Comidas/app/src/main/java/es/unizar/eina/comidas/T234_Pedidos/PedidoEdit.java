package es.unizar.eina.comidas.T234_Pedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import es.unizar.eina.T234_Comidas.R;
import es.unizar.eina.comidas.T234_Platos.PlatoEdit;

/** Pantalla utilizada para la creación o edición de un pedido */
public class PedidoEdit extends AppCompatActivity {

    public static final String PEDIDO_NOMBRE_CLIENTE = "nombreClientePedido";
    public static final String PEDIDO_TELEFONO_CLIENTE = "telefonoClientePedido";
    public static final String PEDIDO_ESTADO = "estadoPedido";
    public static final String PEDIDO_FECHA_RECOGIDA = "fechaPedidoRecogida";
    public static final String PEDIDO_HORA_RECOGIDA = "horaPedidoRecogida";
    public static final String PEDIDO_PRECIO = "precioPedido";
    public static final String PEDIDO_ID = "id";

    //Lista de platos y raciones ??

    private EditText mNombreClienteText;
    private EditText mTelefonoClienteText;
    private EditText mEstadoText;
    private EditText mFechaRecogidaText;
    private EditText mHoraRecogidaText;

    private EditText mPrecioPedidoText;

    private Integer mRowId;

    Button mSaveButton;

    PedidoViewModel mPedidoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoedit);
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        mNombreClienteText = (EditText) findViewById(R.id.nombreClientePedido);
        mTelefonoClienteText = (EditText) findViewById(R.id.telefonoClientePedido);
        mEstadoText = (EditText) findViewById(R.id.estadoPedido);
        mFechaRecogidaText = (EditText) findViewById(R.id.fechaPedidoRecogida);
        mHoraRecogidaText = (EditText) findViewById(R.id.horaPedidoRecogida);
        mPrecioPedidoText = (EditText) findViewById(R.id.precioPedido);

        mSaveButton = findViewById(R.id.button_pedidos);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (!validarPedido()){
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String nombreCliente = (String) mNombreClienteText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_NOMBRE_CLIENTE, nombreCliente);
                Integer telefono = Integer.parseInt(mTelefonoClienteText.getText().toString());
                replyIntent.putExtra(PedidoEdit.PEDIDO_TELEFONO_CLIENTE, telefono);
                String estado = (String) mEstadoText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_ESTADO, estado);
                String fecha = mFechaRecogidaText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_FECHA_RECOGIDA, fecha);
                String hora = mHoraRecogidaText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_HORA_RECOGIDA, hora);
                Double precioPedido = Double.parseDouble(calcularPrecioPedido());
                replyIntent.putExtra(PedidoEdit.PEDIDO_PRECIO, precioPedido);
                if (mRowId != null) {
                    replyIntent.putExtra(PedidoEdit.PEDIDO_ID, mRowId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
        populateFields();

    }

    private void populateFields () {
        mRowId = null;
        Bundle extras = getIntent().getExtras();

        if (extras!=null) {
            mNombreClienteText.setText(extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE));
            Integer telefono = extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE);
            mTelefonoClienteText.setText(telefono.toString());
            mEstadoText.setText(extras.getString(PedidoEdit.PEDIDO_ESTADO));
            mFechaRecogidaText.setText(extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA));
            mHoraRecogidaText.setText(extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA));
            Double precio = extras.getDouble(PedidoEdit.PEDIDO_PRECIO);
            mPrecioPedidoText.setText(precio.toString());
            mRowId = extras.getInt(PedidoEdit.PEDIDO_ID);
        }
    }

    private String calcularPrecioPedido(){
        double precio = 20;

        return precio+"";
    }

    private Boolean validarPedido(){
        //TextUtils.isEmpty(mNombreClienteText.getText()) ||
        //TextUtils.isEmpty(mTelefonoClienteText.getText()) ||
        //TextUtils.isEmpty(mEstadoText.getText()) ||
        //TextUtils.isEmpty(mFechaRecogidaText.getText()) ||
        //TextUtils.isEmpty(mHoraRecogidaText.getText())
        return true;
    }

}
