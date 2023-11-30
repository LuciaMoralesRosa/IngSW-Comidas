package es.unizar.eina.comidas.T234_Platos;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.T234_Comidas.R;

/**
 * ViewHolder para mostrar la vista de un plato en el RecyclerView. Implementa la interfaz
 * View.OnCreateContextMenuListener para manejar la creación de menús contextuales.
 * @author Lucia Morales
 * @author Curro Valero
 */
class PlatoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    /** TextView para mostrar el nombre del plato. */
    private final TextView mPlatoItemView;

    /**
     * Constructor privado que toma una vista como parámetro.
     *
     * @param itemView La vista del elemento del RecyclerView.
     */
    private PlatoViewHolder(View itemView) {
        super(itemView);
        mPlatoItemView = itemView.findViewById(R.id.textView);
        // Establece este ViewHolder como listener para la creación del menú contextual.
        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Enlaza los datos del plato al TextView.
     *
     * @param text El nombre del plato a mostrar.
     */
    public void bind(String text) {
        mPlatoItemView.setText(text);
    }

    /**
     * Crea una nueva instancia de PlatoViewHolder inflando el diseño de la vista del elemento del RecyclerView.
     *
     * @param parent El ViewGroup al que se adjuntará el nuevo ViewHolder.
     * @return Una nueva instancia de PlatoViewHolder.
     */
    static PlatoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PlatoViewHolder(view);
    }

    /**
     * Se llama cuando se está creando el menú contextual para este ViewHolder.
     *
     * @param menu El menú contextual que se está creando.
     * @param v La vista a la que se ha aplicado el menú contextual.
     * @param menuInfo Información adicional sobre el menú contextual.
     */
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, Platos.DELETE_ID, Menu.NONE, R.string.menu_delete);
        menu.add(Menu.NONE, Platos.EDIT_ID, Menu.NONE, R.string.menu_edit);
    }
}
