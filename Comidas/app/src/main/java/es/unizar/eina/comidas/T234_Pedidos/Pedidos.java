package es.unizar.eina.comidas.T234_Pedidos;

import es.unizar.eina.send.SendImplementor;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.unizar.eina.comidas.database.Pedido;
import es.unizar.eina.T234_Comidas.R;

import android.net.Uri ;
import android.content.Intent ;

import android.content.pm.PackageInfo ;
import android.content.pm.PackageManager;

import es.unizar.eina.send.*;
/** Pantalla con el listado de pedidos de la aplicación Comidas */

public class Pedidos extends AppCompatActivity {
    private PedidoViewModel mPedidoViewModel;

    public static final int ACTIVITY_CREATE = 1;

    public static final int ACTIVITY_EDIT = 2;

   // public static final int ACTIVITY_SEND = 3;

    static final int INSERT_ID = Menu.FIRST;
    static final int DELETE_ID = Menu.FIRST + 1;
    static final int EDIT_ID = Menu.FIRST + 2;
    static final int SEND_ID = Menu.FIRST + 3;

    RecyclerView mRecyclerView;

    PedidoListAdapter mAdapter;

    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        mPedidoViewModel.getAllPedidos().observe(this, pedidos -> {
            // Update the cached copy of the notes in the adapter.
            mAdapter.submitList(pedidos);
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            createPedido();
        });

        // It doesn't affect if we comment the following instruction
        registerForContextMenu(mRecyclerView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, INSERT_ID, Menu.NONE, R.string.add_pedido);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case INSERT_ID:
                createPedido();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
                case ACTIVITY_CREATE: //Creacion de un pedido
                    Pedido newPedido = new Pedido(extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE)
                            , extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE)
                            , extras.getString(PedidoEdit.PEDIDO_ESTADO)
                            , extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA)
                            , extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA)
                            , extras.getDouble(PedidoEdit.PEDIDO_PRECIO));
                    mPedidoViewModel.insert(newPedido);
                    break;
                case ACTIVITY_EDIT: //Edicion de un pedido
                    int id = extras.getInt(PedidoEdit.PEDIDO_ID);
                    Pedido updatedPedido = new Pedido(extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE)
                            , extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE)
                            , extras.getString(PedidoEdit.PEDIDO_ESTADO)
                            , extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA)
                            , extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA)
                            , extras.getDouble(PedidoEdit.PEDIDO_PRECIO));
                    updatedPedido.setId(id);
                    mPedidoViewModel.update(updatedPedido);
                    break;
                default:
                    break;
            }
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        Pedido current = mAdapter.getCurrent();
        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(
                        getApplicationContext(),
                        "Deleting " + current.getNombreCliente(),
                        Toast.LENGTH_LONG).show();
                mPedidoViewModel.delete(current);
                return true;
            case EDIT_ID:
                editPedido(current);
                return true;
            case SEND_ID:
                sendPedido(current);
        }
        return super.onContextItemSelected(item);
    }

    private void createPedido() {
        Intent intent = new Intent(this, PedidoEdit.class);
        startActivityForResult(intent, ACTIVITY_CREATE);
    }

    private void editPedido(Pedido current) {
        Intent intent = new Intent(this, PedidoEdit.class);
        intent.putExtra(PedidoEdit.PEDIDO_NOMBRE_CLIENTE, current.getNombreCliente());
        intent.putExtra(PedidoEdit.PEDIDO_TELEFONO_CLIENTE, current.getTelefonoCliente());
        intent.putExtra(PedidoEdit.PEDIDO_ESTADO, current.getEstado());
        intent.putExtra(PedidoEdit.PEDIDO_PRECIO, current.getPrecioPedido());
        intent.putExtra(PedidoEdit.PEDIDO_ID, current.getId());
        intent.putExtra(PedidoEdit.PEDIDO_HORA_RECOGIDA, current.getHoraRecogida());
        intent.putExtra(PedidoEdit.PEDIDO_FECHA_RECOGIDA, current.getFechaRecogida());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }
    private void sendPedido(Pedido current){
        int phone = current.getTelefonoCliente();
        String message = "Hola" + current.getNombreCliente() + " \n" +
                        "Los detalles de su pedido son: \n" +
                        "Estado: " + current.getEstado() + " \n" +
                        "Fecha de recogida: " + current.getFechaRecogida() + " \n" +
                        "Hora de recogida: " + current.getHoraRecogida() + " \n" +
                        "Precio total: " + current.getPrecioPedido() + " \n";
       /* PackageManager pm = getSourceActivity().getPackageManager ();
        boolean app_installed = false;
        try {pm.getPackageInfo ("com. whatsapp ", PackageManager.GET_ACTIVITIES ) ;
            app_installed = true;
        } catch ( PackageManager.NameNotFoundException e ) {
            app_installed = false;
        }
        if ( app_installed ) {
            // Crear intent y lanzar actividad
            Uri smsUri = Uri.parse ("sms:" + phone);
            Intent sendIntent = new Intent (Intent.ACTION_SENDTO , smsUri );
            sendIntent.putExtra (Intent.EXTRA_TEXT , message );
            sendIntent.setPackage ("com. whatsapp ");
            getSourceActivity().startActivity(sendIntent);
        } else {
            Toast.makeText(getSourceActivity() , " WhatsApp not Installed ",
                            Toast.LENGTH_SHORT).show() ;
        }*/
    }
}
