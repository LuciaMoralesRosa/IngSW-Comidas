package es.unizar.eina.comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
/*
public class RacionesRepository {

    private RacionesDao mRacionesDao;
    private LiveData<List<PedidoPlatoCrossRef>> mAllRaciones;

    // Note that in order to unit test the PedidoRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public RacionesRepository(Application application) {
        ComidasRoomDatabase db = ComidasRoomDatabase.getDatabase(application);
        mRacionesDao = db.racionesDao();
        mAllRaciones = mRacionesDao.getAllRaciones();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PedidoPlatoCrossRef>> getAllPedidos() {
        return mAllRaciones;
    }

    public long insert(PedidoPlatoCrossRef raciones) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionesDao.insert(raciones);
        });
        return result[0];
    }


    public int update(PedidoPlatoCrossRef raciones) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionesDao.update(raciones);
        });
        return result[0];
    }


    public int delete(PedidoPlatoCrossRef raciones) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionesDao.delete(raciones);
        });
        return result[0];
    }
}
*/