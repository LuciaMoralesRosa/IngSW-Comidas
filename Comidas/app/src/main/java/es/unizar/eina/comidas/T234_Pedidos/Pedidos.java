package es.unizar.eina.comidas.T234_Pedidos;

import es.unizar.eina.comidas.T234_Platos.PlatoViewModel;
import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.send.SendImplementor;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.unizar.eina.send.*;
/** Pantalla con el listado de pedidos de la aplicación Comidas */

public class Pedidos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private Activity sourceActivity;

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

    Integer numeroDePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        mRecyclerView = findViewById(R.id.recyclerview_Pedidos);
        mAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        mPedidoViewModel.getAllPedidos().observe(this, pedidos -> {
            numeroDePedidos = 0;
            for(Pedido pedido : pedidos){
                numeroDePedidos++;
            }
            // Update the cached copy of the notes in the adapter.
            mAdapter.submitList(pedidos);
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            createPedido();
        });

        Spinner spinnerOrdenar = findViewById(R.id.spinnerOrdenar_Pedidos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.ordenarPedidos,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapter);
        spinnerOrdenar.setOnItemSelectedListener(this);

        Spinner spinnerFiltrar = findViewById(R.id.spinnerFiltrar_Pedidos);
        ArrayAdapter<CharSequence> adapterFiltrar = ArrayAdapter.
                createFromResource(this, R.array.filtrarPedidos,
                        android.R.layout.simple_spinner_item);
        adapterFiltrar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltrar.setAdapter(adapterFiltrar);
        spinnerFiltrar.setOnItemSelectedListener(this);

        // It doesn't affect if we comment the following instruction
        registerForContextMenu(mRecyclerView);
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
            switch (resultCode){
                case 2:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.estado_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.telefono_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.momento_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.lunes_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 7:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.fuera_horario_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 8:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.fecha_formato_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
                case 9:
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.hora_formato_not_saved_pedidos,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        } else {
            switch (requestCode) {
                case ACTIVITY_CREATE: //Creacion de un pedido
                    if(numeroDePedidos == 2000){
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.not_saved_full_pedidos,
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Pedido newPedido = new Pedido(extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE)
                                , extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE)
                                , extras.getString(PedidoEdit.PEDIDO_ESTADO)
                                , extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA)
                                , extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA)
                                , extras.getDouble(PedidoEdit.PEDIDO_PRECIO));
                        mPedidoViewModel.insert(newPedido);
                    }
                    break;
                case ACTIVITY_EDIT: //Edicion de un pedido
                    int pedidoId = extras.getInt(PedidoEdit.PEDIDO_ID);
                    Pedido updatedPedido = new Pedido(
                              extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE)
                            , extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE)
                            , extras.getString(PedidoEdit.PEDIDO_ESTADO)
                            , extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA)
                            , extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA)
                            , extras.getDouble(PedidoEdit.PEDIDO_PRECIO));
                    updatedPedido.setPedidoId(pedidoId);
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
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void createPedido() {
        Intent intent = new Intent(this, PedidoEdit.class);
        intent.putExtra(PedidoEdit.PEDIDO_ESTADO, "SOLICITADO");
        startActivityForResult(intent, ACTIVITY_CREATE);
    }

    private void editPedido(Pedido current) {
        Intent intent = new Intent(this, PedidoEdit.class);
        intent.putExtra(PedidoEdit.PEDIDO_NOMBRE_CLIENTE, current.getNombreCliente());
        intent.putExtra(PedidoEdit.PEDIDO_TELEFONO_CLIENTE, current.getTelefonoCliente());
        intent.putExtra(PedidoEdit.PEDIDO_ESTADO, current.getEstado());
        intent.putExtra(PedidoEdit.PEDIDO_PRECIO, current.getPrecioPedido());
        intent.putExtra(PedidoEdit.PEDIDO_HORA_RECOGIDA, current.getHoraRecogida());
        intent.putExtra(PedidoEdit.PEDIDO_FECHA_RECOGIDA, current.getFechaRecogida());
        intent.putExtra(PedidoEdit.PEDIDO_ID, current.getPedidoId());
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mPedidoViewModel.getAllPedidos().observe(this, pedidos -> {
            List<Pedido> listaPedidos = new ArrayList<>(pedidos);
            String itemSelected = parent.getItemAtPosition(position).toString();
            switch (itemSelected) {
                case "Ordenar por nombre del cliente":
                    listaPedidos.sort(Comparator.comparing(Pedido::getNombreCliente,
                                      String.CASE_INSENSITIVE_ORDER));
                    break;
                case "Ordenar por telefono":
                    listaPedidos.sort(Comparator.comparing(Pedido::getTelefonoCliente));
                    break;
                case "Ordenar por momento de recogida":
                    listaPedidos.sort(Comparator.comparing(Pedido::getFechaRecogida).
                            thenComparing(Pedido::getHoraRecogida));
                    break;
                case "Ordenar por todo":
                    listaPedidos.sort(Comparator.comparing(Pedido::getNombreCliente,
                                      String.CASE_INSENSITIVE_ORDER).
                            thenComparing(Pedido::getTelefonoCliente).
                            thenComparing(Pedido::getFechaRecogida).
                            thenComparing(Pedido::getHoraRecogida));
                    break;
                case "Mostrar solo solicitados":
                    List<Pedido> listaFiltradaSolicitado = new ArrayList<>();
                    for(Pedido pedido : pedidos){
                        if(pedido.getEstado().equals("SOLICITADO")){
                            listaFiltradaSolicitado.add(pedido);
                        }
                    }
                    listaPedidos = listaFiltradaSolicitado;
                    break;
                case "Mostrar solo preparados":
                    List<Pedido> listaFiltradaPreparados = new ArrayList<>();
                    for(Pedido pedido : pedidos){
                        if(pedido.getEstado().equals("PREPARADO")){
                            listaFiltradaPreparados.add(pedido);
                        }
                    }
                    listaPedidos = listaFiltradaPreparados;
                    break;
                case "Mostrar solo recogidos":
                    List<Pedido> listaFiltradaRecogidos = new ArrayList<>();
                    for(Pedido pedido : pedidos){
                        if(pedido.getEstado().equals("RECOGIDO")){
                            listaFiltradaRecogidos.add(pedido);
                        }
                    }
                    listaPedidos = listaFiltradaRecogidos;
                    break;
                default:
                    break;
            }
            mAdapter.submitList(listaPedidos);
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
