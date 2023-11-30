package es.unizar.eina.comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Base de datos Room que sirve como contenedor para la entidad Plato y su correspondiente DAO.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
@Database(entities = {Plato.class}, version = 1, exportSchema = false)
public abstract class PlatoRoomDatabase extends RoomDatabase {

    /** DAO para la entidad Plato. */
    public abstract PlatoDao platoDao();

    /** Instancia única de la base de datos. */
    private static volatile PlatoRoomDatabase INSTANCE;

    /** Número de hilos para las operaciones en la base de datos. */
    private static final int NUMBER_OF_THREADS = 4;

    /** ExecutorService para ejecutar operaciones de escritura en la base de datos en hilos
     * separados. */
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Obtiene la instancia única de la base de datos o crea una nueva si no existe.
     *
     * @param context El contexto de la aplicación.
     * @return La instancia única de la base de datos.
     */
    static PlatoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlatoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlatoRoomDatabase.class, "plato_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /** Callback para ejecutar operaciones al crear la base de datos. */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            /* Si desea conservar los datos a través de reinicios de la aplicación, comente el
            *  siguiente bloque.*/
            databaseWriteExecutor.execute(() -> {
                /* Poblar la base de datos en segundo plano. Si desea comenzar con más platos,
                   simplemente agréguelos.*/
                PlatoDao dao = INSTANCE.platoDao();
                dao.deleteAll();

                Plato plato = new Plato("Macarrones con tomate", "Macarrones con tomate bacon y" +
                        "queso", "PRIMERO", 12.5);
                dao.insert(plato);
                plato = new Plato("Lubina", "Lubina al horno con patatas", "SEGUNDO", 13.8);
                dao.insert(plato);
            });
        }
    };
}
