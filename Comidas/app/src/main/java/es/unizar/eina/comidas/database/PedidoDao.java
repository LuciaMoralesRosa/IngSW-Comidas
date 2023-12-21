package es.unizar.eina.comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object para los pedidos */
@Dao
public interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Pedido pedido);

    @Update
    int update(Pedido pedido);

    @Delete
    int delete(Pedido pedido);

    @Query("DELETE FROM pedido")
    void deleteAll();

    @Query("SELECT * FROM pedido ORDER BY nombreCliente ASC")
    LiveData<List<Pedido>> getOrderedPedidos();

    //@Transaction
    //@Query("SELECT * FROM pedido")
    //public List<RacionesConPlatos> getRacionesConPlatos();
}