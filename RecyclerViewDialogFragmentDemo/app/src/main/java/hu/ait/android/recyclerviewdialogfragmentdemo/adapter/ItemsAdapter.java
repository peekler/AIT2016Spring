package hu.ait.android.recyclerviewdialogfragmentdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.recyclerviewdialogfragmentdemo.MainActivity;
import hu.ait.android.recyclerviewdialogfragmentdemo.R;
import hu.ait.android.recyclerviewdialogfragmentdemo.data.Item;

public class ItemsAdapter
        extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private List<Item> items = new ArrayList<Item>();

    public ItemsAdapter(Context context) {
        this.context = context;
        for (int j = 0; j < 3; j++) {
            items.add(new Item("Todo " + j));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvItem.setText(items.get(position).getItemText());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showItemText(items.get(holder.getAdapterPosition()).getItemText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item) {
        items.add(0, item);
        notifyItemInserted(0);
    }

    public void removeItem(int index) {
        items.remove(index);
        notifyItemRemoved(index);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tvItem);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}
