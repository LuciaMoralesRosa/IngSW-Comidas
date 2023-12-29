package es.unizar.eina.comidas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.LinkedList;
import java.util.List;

/** Clase anotada como entidad que representa un pedido y que consta de nombre del cliente que
 * realiza el pedido, el numero de teléfono de dicho cliente, la fecha y hora de recogida del
 * pedido y su precio. //Faltaria la lista y las raciones
 * */
@Entity(tableName = "pedido")
public class Pedido{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pedidoId")
    private int pedidoId;

    @NonNull
    @ColumnInfo(name = "nombreCliente")
    private String nombreCliente;

    @NonNull
    @ColumnInfo(name = "telefonoCliente")
    private Integer telefonoCliente;

    @NonNull
    @ColumnInfo(name = "estado")
    private String estado; //Emun SOLICITADO, PREPARADO, RECOGIDO

    @NonNull
    @ColumnInfo(name = "fechaRecogida")
    private String fechaRecogida;

    @NonNull
    @ColumnInfo(name = "horaRecogida")
    private String horaRecogida;

    @NonNull
    @ColumnInfo(name = "precioPedido")
    private Double precioPedido;

    //@NonNull
    //@ColumnInfo(name = "listaPlatos")
    //private List<Plato> listaPlatos;

    //Lista de platos y raciones??
    public Pedido(@NonNull String nombreCliente, @NonNull Integer telefonoCliente,
                  @NonNull String estado, @NonNull String fechaRecogida,
                  @NonNull String horaRecogida, @NonNull Double precioPedido) {
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.estado = estado;
        this.fechaRecogida = fechaRecogida;
        this.horaRecogida = horaRecogida;
        this.precioPedido = precioPedido;
    }

    /** Devuelve el identificador del pedido */
    public int getPedidoId(){
        return this.pedidoId;
    }

    /** Permite actualizar el identificador de un pedido */
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    /** Devuelve el nombre del cliente que ha realizado el pedido */
    public String getNombreCliente(){
        return this.nombreCliente;
    }

    /** Devuelve el numero de telefono del cliente que ha realizado el pedido */
    public Integer getTelefonoCliente(){
        return this.telefonoCliente;
    }

    /** Devuelve el estado del pedido */
    public String getEstado(){
        return this.estado;
    }

    /** Devuelve la fecha de recogida del pedido */
    public String getFechaRecogida(){
        return this.fechaRecogida;
    }

    /** Devuelve la hora de recogida del pedido */
    public String getHoraRecogida(){
        return this.horaRecogida;
    }

    /** Devuelve el precio del pedido */
    public Double getPrecioPedido(){
        return this.precioPedido;
    }

    //Faltaria la lista??
}
