package hu.bme.aut.android.mvpbasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainScreen {

    private EditText etNumA;
    private EditText etNumB;
    private TextView tvResult;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);

        etNumA = (EditText) findViewById(R.id.etNumA);
        etNumB = (EditText) findViewById(R.id.etNumB);
        tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(etNumA.getText().toString());
                int b = Integer.parseInt(etNumB.getText().toString());
                //tvResult.setText(String.valueOf(a + b));


                mainPresenter.addNumbers(a, b);
            }
        });
    }

    @Override
    public void showCalcResult(int result) {
        tvResult.setText(Integer.valueOf(result));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
