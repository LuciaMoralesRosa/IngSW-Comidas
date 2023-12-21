package es.unizar.eina.comidas.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;


public class RacionesConPlatos {
    @Embedded
    public Pedido pedido;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(RacionesConPlatosRef.class)
    )
    public List<Plato> platosDeLaRacion;
}
