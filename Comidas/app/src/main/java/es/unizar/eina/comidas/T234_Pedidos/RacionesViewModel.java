package es.unizar.eina.comidas.T234_Pedidos;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.comidas.database.Pedido;
import es.unizar.eina.comidas.database.PedidoRepository;
/*
public class RacionesViewModel extends AndroidViewModel {

    private PedidoRepository mRepository;

    private final LiveData<List<Pedido>> mAllPedidos;

    public RacionesViewModel(Application application) {
        super(application);
        mRepository = new PedidoRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
    }

    LiveData<List<Pedido>> getAllPedidos() { return mAllPedidos; }

    public void insert(Pedido pedido) {
        mRepository.insert(pedido);
    }

    public void update(Pedido pedido) {
        mRepository.update(pedido);
    }
    public void delete(Pedido pedido) {
        mRepository.delete(pedido);
    }
}
*/
