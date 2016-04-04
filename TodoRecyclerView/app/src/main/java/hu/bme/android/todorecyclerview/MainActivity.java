package hu.bme.android.todorecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.bme.android.todorecyclerview.adapter.TodoAdapter;
import hu.bme.android.todorecyclerview.adapter.TodoItemTouchHelperCallback;
import hu.bme.android.todorecyclerview.data.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final TodoAdapter todoRecyclerAdapter = new TodoAdapter(this);
        final RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // RecyclerView layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoRecyclerAdapter);

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);


        final EditText etTodoTitle = (EditText) findViewById(R.id.etTodoTitle);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoRecyclerAdapter.addTodo(new Todo(
                        etTodoTitle.getText().toString(), false));
            }
        });

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoRecyclerAdapter.addTodo(new Todo(
                        etTodoTitle.getText().toString(), false));

                Snackbar.make(v, "Item added", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                todoRecyclerAdapter.removeTodo(0);
                            }
                        }).show();
            }
        });
    }
}
