package hu.bme.aut.android.activityresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hu.bme.aut.android.activityresultdemo.data.City;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_NEW_CITY = 101;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);
        Button btnNew = (Button) findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newCityIntent = new Intent();
                newCityIntent.setClass(MainActivity.this,
                        NewCityActivity.class);
                startActivityForResult(newCityIntent, REQUEST_NEW_CITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_NEW_CITY) {
            if (resultCode == RESULT_OK) {

                City city = (City) data.getSerializableExtra(
                        NewCityActivity.KEY_CITY);
                tvData.append(city.getName()+"-"+city.getDesc()+"\n");

            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(MainActivity.this,
                        "City create was cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
