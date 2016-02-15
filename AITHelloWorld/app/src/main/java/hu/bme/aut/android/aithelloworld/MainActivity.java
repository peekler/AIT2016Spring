package hu.bme.aut.android.aithelloworld;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutMain = (LinearLayout) findViewById(R.id.layoutMain);

        etName = (EditText) findViewById(R.id.etName);

        final TextView tvDate = (TextView) findViewById(R.id.tvDate);
        Button btnTime = (Button) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButtonClick(tvDate);
            }
        });
    }

    private void handleTimeButtonClick(TextView tvDate) {
        Log.d("TAG_ACTIVITY", "Time button was clicked!");


        String textDate =
                etName.getText().toString() +
                ", the time is: " + new Date(System.currentTimeMillis()).toString();
        Toast.makeText(MainActivity.this,
                textDate,
                Toast.LENGTH_LONG).show();
        tvDate.setText(textDate);
        tvDate.setTextColor(Color.GREEN);

        Snackbar.make(layoutMain, textDate,
                Snackbar.LENGTH_LONG).show();

    }


}
