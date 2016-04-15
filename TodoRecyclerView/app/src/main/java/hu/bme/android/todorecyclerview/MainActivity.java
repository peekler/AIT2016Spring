package hu.bme.android.todorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hu.bme.android.todorecyclerview.adapter.TodoAdapter;
import hu.bme.android.todorecyclerview.adapter.TodoItemTouchHelperCallback;
import hu.bme.android.todorecyclerview.data.Todo;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_TODO = 101;
    public static final int REQUEST_CODE_EDIT_TODO = 102;
    public static final String KEY_EDIT = "KEY_EDIT";

    private TodoAdapter todoRecyclerAdapter;

    private Todo todoEditHolder;
    private int todoToEditPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        todoRecyclerAdapter = new TodoAdapter(this);
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

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTodoActivity();
            }
        });
    }

    private void showAddTodoActivity() {
        Intent intentAddTodo = new Intent(MainActivity.this, AddTodoActivity.class);
        startActivityForResult(intentAddTodo,
                REQUEST_CODE_ADD_TODO);
    }

    public void showEditTodoActivity(Todo todoToEdit, int position) {
        Intent intentEditTodo = new Intent(MainActivity.this,
                AddTodoActivity.class);
        todoEditHolder = todoToEdit;
        todoToEditPosition = position;

        intentEditTodo.putExtra(KEY_EDIT, todoToEdit);
        startActivityForResult(intentEditTodo, REQUEST_CODE_EDIT_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_CODE_ADD_TODO) {
                    Todo todo = (Todo) data.getSerializableExtra(
                            AddTodoActivity.KEY_TODO);

                    todoRecyclerAdapter.addTodo(todo);
                } else if (requestCode == REQUEST_CODE_EDIT_TODO) {
                    Todo todoTemp = (Todo) data.getSerializableExtra(
                            AddTodoActivity.KEY_TODO);

                    todoEditHolder.setTodo(todoTemp.getTodo());
                    todoEditHolder.setDone(todoTemp.isDone());

                    if (todoToEditPosition != -1) {
                        todoRecyclerAdapter.updateTodo(todoToEditPosition, todoEditHolder);
                        todoToEditPosition = -1;
                    }else {
                        todoRecyclerAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case RESULT_CANCELED:
                Toast.makeText(MainActivity.this, R.string.text_cancelled, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_todo:
                showAddTodoActivity();
                break;
            default:
                break;
        }

        return true;
    }
}
