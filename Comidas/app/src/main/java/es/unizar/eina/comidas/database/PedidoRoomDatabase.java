package es.unizar.eina.comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pedido.class}, version = 1, exportSchema = false)
public abstract class PedidoRoomDatabase extends RoomDatabase {

    public abstract PedidoDao pedidoDao();

    private static volatile PedidoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PedidoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PedidoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PedidoRoomDatabase.class, "pedido_database")
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
                PedidoDao dao = INSTANCE.pedidoDao();
                dao.deleteAll();

                Pedido pedido = new Pedido("Pepe Garcia", 976332456,
                        "SOLICITADO", "2023/05/12", "11:11", 20.05);
                dao.insert(pedido);
                pedido = new Pedido("Maria Rosa", 976337456,
                        "PREPARADO", "2021/06/30", "14:30", 25.05);
                dao.insert(pedido);
            });
        }
    };

}
