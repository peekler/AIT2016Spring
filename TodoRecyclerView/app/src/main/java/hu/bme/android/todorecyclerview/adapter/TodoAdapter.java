package hu.bme.android.todorecyclerview.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.bme.android.todorecyclerview.R;
import hu.bme.android.todorecyclerview.data.Todo;

public class TodoAdapter
        extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private Context context;
    private List<Todo> todos = new ArrayList<Todo>();

    public TodoAdapter(Context context) {
        this.context = context;


        todos = Todo.listAll(Todo.class);

        /*for (int j = 0; j < 20; j++) {
            todos.add(new Todo("Todo " + j, false));
        }*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTodo.setText(todos.get(position).getTodo());
        holder.cbDone.setChecked(todos.get(position).isDone());

        holder.cbDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = todos.get(position);
                todo.setDone(holder.cbDone.isChecked());

                todo.save();

                // we will save/update todo object in the DataBase here

                Toast.makeText(context,
                        todo.getTodo()+": "+
                        todo.isDone(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void addTodo(Todo todo) {
        todos.add(0, todo);

        todo.save();

        // ths refreshes the whole list
        notifyDataSetChanged();

        // this refreshes only the first item, more optimal!
        //notifyItemInserted(0);
    }

    public void removeTodo(int position) {
        todos.get(position).delete();
        todos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDismiss(int position) {
        removeTodo(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todos, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todos, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
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
