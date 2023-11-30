package es.unizar.eina.comidas.T234_Pedidos;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.T234_Comidas.R;

class PedidoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private final TextView mPedidoItemView;



    private PedidoViewHolder(View itemView) {
        super(itemView);
        mPedidoItemView = itemView.findViewById(R.id.textView);

        itemView.setOnCreateContextMenuListener(this);
    }

    public void bind(String text) {
        mPedidoItemView.setText(text);
    }

    static es.unizar.eina.comidas.T234_Pedidos.PedidoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new es.unizar.eina.comidas.T234_Pedidos.PedidoViewHolder(view);
    }


    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, Pedidos.DELETE_ID, Menu.NONE, R.string.menu_delete);
        menu.add(Menu.NONE, Pedidos.EDIT_ID, Menu.NONE, R.string.menu_edit);
        menu.add(Menu.NONE, Pedidos.SEND_ID, Menu.NONE, R.string.menu_send);

    }


}
