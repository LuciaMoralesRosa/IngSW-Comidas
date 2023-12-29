package es.unizar.eina.comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pedido.class, Plato.class, PedidoPlatoCrossRef.class}, version = 1,
        exportSchema = false)
public abstract class ComidasRoomDatabase extends RoomDatabase {

    public abstract PedidoDao pedidoDao();
    public abstract PlatoDao platoDao();
    public abstract RacionesDao racionesDao();

    private static volatile ComidasRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ComidasRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ComidasRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ComidasRoomDatabase.class, "comidas_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more plates, just add them.

                //Pedido
                PedidoDao pedido_dao = INSTANCE.pedidoDao();
                pedido_dao.deleteAll();

                Pedido pedido = new Pedido("Pepe Garcia", 976332456,
                        "SOLICITADO", "2023/05/12", "11:11", 20.05);
                pedido_dao.insert(pedido);
                pedido = new Pedido("Maria Rosa", 976337456,
                        "PREPARADO", "2021/06/30", "14:30", 25.05);
                pedido_dao.insert(pedido);

                //Plato
                PlatoDao plato_dao = INSTANCE.platoDao();
                plato_dao.deleteAll();
                Plato plato = new Plato("Macarrones con tomate", "Macarrones con tomate bacon y" +
                        "queso", "PRIMERO", 12.5);
                plato_dao.insert(plato);
                plato = new Plato("Lubina", "Lubina al horno con patatas", "SEGUNDO", 13.8);
                plato_dao.insert(plato);

                //Raciones
                RacionesDao raciones_dao = INSTANCE.racionesDao();
                raciones_dao.deleteAll();
            });
        }
    };

}
