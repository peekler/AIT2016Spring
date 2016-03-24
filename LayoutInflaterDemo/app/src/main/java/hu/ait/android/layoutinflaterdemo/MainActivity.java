package hu.ait.android.layoutinflaterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.etTodo)
    EditText etTodo;
    @Bind(R.id.layoutTodoContainer)
    LinearLayout layoutTodoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTodo)
    public void btnTodoSaveClicket(Button btn) {
        LayoutInflater inflater = getLayoutInflater();

        View todoRow = inflater.inflate(R.layout.todo_row,
                layoutTodoContainer,
                false);
        TextView tvTodoText = (TextView) todoRow.findViewById(R.id.tvTodoText);
        tvTodoText.setText(etTodo.getText().toString());


        // new elements goes to the beginning of the list
        layoutTodoContainer.addView(todoRow, 0);
    }

    @OnClick(R.id.btnDeleteAll)
    public void btnDeleteAll(Button btn) {
        layoutTodoContainer.removeAllViews();
    }
}
