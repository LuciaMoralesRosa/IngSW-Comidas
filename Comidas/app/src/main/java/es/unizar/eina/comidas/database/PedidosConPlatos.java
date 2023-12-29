package es.unizar.eina.comidas.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;


/**
 * La clase PedidosConPlatos representa la asociación entre un Pedido y la lista de Platos
 * asociados a ese pedido. Esta clase se utiliza para representar la relación muchos a muchos
 * entre Pedidos y Platos.
 *
 * Se utiliza la anotación @Embedded para indicar que la instancia de Pedido será incrustada
 * en esta clase. Además, la anotación @Relation se utiliza para definir la relación entre
 * Pedidos y Platos mediante la especificación de columnas relacionadas y el uso de la
 * clase de asociación PedidoPlatoCrossRef.
 *
 * @see Pedido
 * @see Plato
 * @see PedidoPlatoCrossRef
 */
public class PedidosConPlatos {
    /**
     * Instancia del Pedido asociado.
     */
    @Embedded
    public Pedido pedido;

    /**
     * Lista de Platos asociados al Pedido, representando la relación muchos a muchos.
     */
    @Relation(
            parentColumn = "pedidoId",
            entityColumn = "platoId",
            associateBy = @Junction(PedidoPlatoCrossRef.class)
    )
    public List<Plato> platosDeLaRacion;
}
