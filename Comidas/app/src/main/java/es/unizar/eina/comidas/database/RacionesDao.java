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

/**
 * Definición de un Data Access Object (DAO) para las raciones en la base de datos.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
@Dao
public interface RacionesDao {

    /**
     * Inserta una nueva racion en la base de datos.
     *
     * @param racion La racion que se va a insertar.
     * @return El identificador único de la racion recién insertada.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(PedidoPlatoCrossRef racion);

    /**
     * Actualiza una racion existente en la base de datos.
     *
     * @param racion La racion actualizada que se va a almacenar.
     * @return El número de filas afectadas en la base de datos.
     */
    @Update
    int update(PedidoPlatoCrossRef racion);

    /**
     * Elimina una racion de la base de datos.
     *
     * @param racion La racion que se va a eliminar.
     * @return El número de filas afectadas en la base de datos.
     */
    @Delete
    int delete(PedidoPlatoCrossRef racion);

    /**
     * Elimina todas las raciones de la base de datos.
     */
    @Query("DELETE FROM pedido_plato_cross_ref")
    void deleteAll();

    /**
     * Obtiene una lista de todas las raciones.
     *
     * @return LiveData que contiene la lista de raciones.
     */
    @Query("SELECT * FROM pedido_plato_cross_ref ORDER BY id ASC")
    LiveData<List<PedidoPlatoCrossRef>> getAllRaciones();


    @Query("SELECT * FROM pedido_plato_cross_ref WHERE id = :pedidoId")
    List<PedidoPlatoCrossRef> getNumeroRaciones(long pedidoId);

    @Transaction
    @Query("SELECT * FROM pedido WHERE id = :pedidoId")
    PedidosConPlatos getPlatosForPedidos(long pedidoId);
}

