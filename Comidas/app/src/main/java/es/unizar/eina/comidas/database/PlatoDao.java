package es.unizar.eina.comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import es.unizar.eina.comidas.database.PlatoDao;
import java.util.List;

/**
 * Definición de un Data Access Object (DAO) para los platos en la base de datos.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
@Dao
public interface PlatoDao {

    /**
     * Inserta un nuevo plato en la base de datos.
     *
     * @param plato El plato que se va a insertar.
     * @return El identificador único del plato recién insertado.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Plato plato);

    /**
     * Actualiza un plato existente en la base de datos.
     *
     * @param plato El plato actualizado que se va a almacenar.
     * @return El número de filas afectadas en la base de datos.
     */
    @Update
    int update(Plato plato);

    /**
     * Elimina un plato de la base de datos.
     *
     * @param plato El plato que se va a eliminar.
     * @return El número de filas afectadas en la base de datos.
     */
    @Delete
    int delete(Plato plato);

    /**
     * Elimina todos los platos de la base de datos.
     */
    @Query("DELETE FROM plato")
    void deleteAll();

    /**
     * Obtiene una lista de todos los platos ordenados por nombre de forma ascendente.
     *
     * @return LiveData que contiene la lista de platos ordenados.
     */
    @Query("SELECT * FROM plato")
    LiveData<List<Plato>> getAllPlatos();
}

