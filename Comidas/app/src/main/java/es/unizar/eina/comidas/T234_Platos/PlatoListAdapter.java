package es.unizar.eina.comidas.T234_Platos;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.comidas.database.Plato;

/**
 * Adaptador personalizado para la lista de platos. Extiende ListAdapter para gestionar de manera
 * eficiente las actualizaciones de la interfaz de usuario cuando cambian los datos de la lista.
 *
 * @param <Plato> El tipo de datos que representa un plato en la lista.
 * @param <PlatoViewHolder> El tipo de ViewHolder utilizado para mostrar un plato en la interfaz de
 * usuario.
 *
 * @author Lucia Morales
 * @author Curro Valero
 */
public class PlatoListAdapter extends ListAdapter<Plato, PlatoViewHolder> {

    /** Posición actual del plato en la lista. */
    private int position;

    /**
     * Obtiene la posición actual del plato en la lista.
     *
     * @return La posición actual del plato.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Establece la posición actual del plato en la lista.
     *
     * @param position La nueva posición del plato.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Constructor del adaptador.
     *
     * @param diffCallback El objeto DiffUtil.ItemCallback que se utiliza para comparar elementos de la lista.
     */
    public PlatoListAdapter(@NonNull DiffUtil.ItemCallback<Plato> diffCallback) {
        super(diffCallback);
    }

    /**
     * Crea y devuelve un nuevo ViewHolder que se utilizará para mostrar elementos en la lista.
     *
     * @param parent El ViewGroup al que se adjuntará el nuevo ViewHolder.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo PlatoViewHolder.
     */
    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolder.create(parent);
    }

    /**
     * Devuelve el plato actual en la posición especificada.
     *
     * @return El plato actual en la posición especificada.
     */
    public Plato getCurrent() {
        return getItem(getPosition());
    }

    /**
     * Llena el ViewHolder con los datos del plato en la posición dada.
     *
     * @param holder El ViewHolder a llenar.
     * @param position La posición del plato en la lista.
     */
    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {

        Plato current = getItem(position);
        holder.bind(current.getNombre());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });
    }


    /**
     * Clase interna que implementa DiffUtil.ItemCallback para comparar platos en la lista.
     */
    static class PlatoDiff extends DiffUtil.ItemCallback<Plato> {

        /**
         * Compara si los elementos son los mismos comparando sus identificadores.
         *
         * @param oldItem El antiguo objeto Plato.
         * @param newItem El nuevo objeto Plato.
         * @return True si los elementos son los mismos, false de lo contrario.
         */
        @Override
        public boolean areItemsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() +
            // " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.getId() == newItem.getId();
        }

        /**
         * Compara si los contenidos de los elementos son los mismos comparando sus nombres.
         *
         * @param oldItem El antiguo objeto Plato.
         * @param newItem El nuevo objeto Plato.
         * @return True si los contenidos son los mismos, false de lo contrario.
         */
        @Override
        public boolean areContentsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() +" vs "
            // + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation
            // i.e. changes in the title
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}
