package es.unizar.eina.comidas.T234_Platos;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.comidas.database.Plato;
import es.unizar.eina.comidas.database.PlatoRepository;

/**
 * ViewModel para la gestión de datos relacionados con los platos. Extiende AndroidViewModel para
 * mantener una referencia a la Application y sobrevivir a los cambios de configuración.
 * @author Lucia Morales
 * @author Curro Valero
 */
public class PlatoViewModel extends AndroidViewModel {

    /** Repositorio para interactuar con la base de datos de platos. */
    private PlatoRepository mRepository;
    /** LiveData que contiene la lista de todos los platos. */
    private final LiveData<List<Plato>> mAllPlatos;
    //private final Integer mNumeroDePlatos;

    /**
     * Constructor de la clase. Inicializa el repositorio y obtiene la lista de todos los platos.
     *
     * @param application La aplicación en la que se encuentra el ViewModel.
     */
    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new PlatoRepository(application);
        mAllPlatos = mRepository.getAllPlatos();
        //mNumeroDePlatos = mRepository.getNumeroDePlatos();
    }

    /**
     * Obtiene el LiveData que contiene la lista de todos los platos.
     *
     * @return LiveData que contiene la lista de todos los platos.
     */
    LiveData<List<Plato>> getAllPlatos() {
        return mRepository.getAllPlatos();
    }

    //Integer getNumeroDePlatos(){
    //    return mRepository.getNumeroDePlatos();
    //}

    /**
     * Inserta un nuevo plato en la base de datos a través del repositorio.
     *
     * @param plato El plato que se va a insertar.
     */
    public void insert(Plato plato) {
        mRepository.insert(plato);
    }

    /**
     * Actualiza un plato existente en la base de datos a través del repositorio.
     *
     * @param plato El plato actualizado que se va a almacenar.
     */
    public void update(Plato plato) {
        mRepository.update(plato);
    }

    /**
     * Elimina un plato de la base de datos a través del repositorio.
     *
     * @param plato El plato que se va a eliminar.
     */
    public void delete(Plato plato) {
        mRepository.delete(plato);
    }
}
