package es.unizar.eina.comidas.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/**
 * Clase que representa la entidad de cruce entre Pedidos y Platos en la base de datos.
 * Esta clase se utiliza para establecer la relación muchos a muchos entre las entidades Pedido y Plato.
 * Cada instancia de esta clase representa la cantidad de un plato específico incluido en un pedido.
 *
 * La tabla asociada en la base de datos se llama "pedido_plato_cross_ref".
 */
@Entity(tableName = "pedido_plato_cross_ref", primaryKeys = {"pedido.id", "plato.id"})
public class PedidoPlatoCrossRef {

    /**
     * Identificador único de la entidad de cruce PedidoPlatoCrossRef en la base de datos.
     */
    public long id;

    /**
     * Identificador único del plato asociado a este cruce en la base de datos.
     */
    public long platoId;

    /**
     * Número de platos del tipo especificado en la asociación entre Pedido y Plato.
     * Puede ser nulo si no se especifica un número.
     */
    public Integer numeroPlatos;
}
