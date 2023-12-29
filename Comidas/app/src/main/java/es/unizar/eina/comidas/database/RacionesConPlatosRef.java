package es.unizar.eina.comidas.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "raciones", primaryKeys = {"pedido.id", "plato.id"})
public class RacionesConPlatosRef {
    public long id;
    public long platoId;
    public Integer numeroPlatos;
}
