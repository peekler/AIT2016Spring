package hu.bme.android.todorecyclerview.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.android.todorecyclerview.R;
import hu.bme.android.todorecyclerview.data.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todos = new ArrayList<Todo>();

    public TodoAdapter(Context context) {
        this.context = context;
        for (int j = 0; j < 20; j++) {
            todos.add(new Todo("Todo " + j, false));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTodo.setText(todos.get(position).getTodo());
        holder.cbDone.setChecked(todos.get(position).isDone());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTodo;
        private CheckBox cbDone;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
            cbDone = (CheckBox) itemView.findViewById(R.id.cbDone);
        }
    }

}
