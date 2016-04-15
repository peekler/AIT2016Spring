package hu.bme.android.todorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import hu.bme.android.todorecyclerview.data.Todo;

public class AddTodoActivity extends AppCompatActivity {
    public static final String KEY_TODO = "KEY_TODO";
    private EditText etTodoText;
    private CheckBox cbTodoDone;
    private Todo todoToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        if (getIntent().getSerializableExtra(MainActivity.KEY_EDIT) != null) {
            todoToEdit = (Todo) getIntent().getSerializableExtra(MainActivity.KEY_EDIT);
        }

        etTodoText = (EditText) findViewById(R.id.etTodoText);
        cbTodoDone = (CheckBox) findViewById(R.id.cbTodoDone);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });

        if (todoToEdit != null) {
            etTodoText.setText(todoToEdit.getTodo());
            cbTodoDone.setChecked(todoToEdit.isDone());
        }
    }

    private void saveTodo() {
        if ("".equals(etTodoText.getText().toString())) {
            etTodoText.setError(getString(R.string.error_field_empty));
        } else {
            Intent intentResult = new Intent();
            Todo todoResult = null;
            if (todoToEdit != null) {
                todoResult = todoToEdit;
            } else {
                todoResult = new Todo();
            }

            todoResult.setTodo(etTodoText.getText().toString());
            todoResult.setDone(cbTodoDone.isChecked());

            intentResult.putExtra(KEY_TODO, todoResult);
            setResult(RESULT_OK, intentResult);
            finish();
        }
    }
}
