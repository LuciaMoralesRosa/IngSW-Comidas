package es.unizar.eina.comidas.database;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/** Clase anotada como entidad que representa un plato y que consta de nombre, descripcion,
 * categoria y precio
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
@Entity(tableName = "plato")
public class Plato{

    /** Identificador único del plato, generado automáticamente por la base de datos. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    /** Nombre del plato. No puede ser nulo. */
    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre; //UNIQUE

    /** Descripción del plato. Puede ser nulo. */
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    /** Categoría del plato. No puede ser nulo. Enumerado como PRIMERO, SEGUNDO, POSTRE. */
    @NonNull
    @ColumnInfo(name = "categoria")
    private String categoria; //Emun PRIMERO, SEGUNDO, POSTRE

    /** Precio del plato. No puede ser nulo. */
    @NonNull
    @ColumnInfo(name = "precio")
    private Double precio;

    /**
     * Constructor de la clase que inicializa los atributos del plato.
     *
     * @param nombre      Nombre del plato.
     * @param descripcion Descripción del plato.
     * @param categoria   Categoría del plato.
     * @param precio      Precio del plato.
     */
    public Plato(@NonNull String nombre, String descripcion, @NonNull String categoria,
                 @NonNull Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    /**
     * Devuelve el identificador único del plato.
     *
     * @return El identificador del plato.
     */
    public int getId(){
        return this.id;
    }

    /**
     * Permite actualizar el identificador único de un plato.
     *
     * @param id El nuevo identificador del plato.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del plato.
     *
     * @return El nombre del plato.
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Devuelve la descripción del plato.
     *
     * @return La descripción del plato.
     */
    public String getDescripcion(){
        return this.descripcion;
    }

    /**
     * Devuelve la categoría del plato.
     *
     * @return La categoría del plato.
     */
    public String getCategoria(){
        return this.categoria;
    }

    /**
     * Devuelve el precio del plato.
     *
     * @return El precio del plato.
     */
    public Double getPrecio(){
        return this.precio;
    }


    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(@NonNull String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(@NonNull Double precio) {
        this.precio = precio;
    }
}
