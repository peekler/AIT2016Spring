package hu.ait.android.viewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static final String[] cityNames = {"Budapest", "Bukarest",
    "New York", "New Jersey", "Los Angeles"};

    @Bind(R.id.autoTvCity)
    AutoCompleteTextView autoTvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line,
                cityNames
        );
        autoTvCity.setAdapter(cityAdapter);
    }


    @OnClick(R.id.btnSubmit)
    public void submitClick(Button btn) {
        Toast.makeText(MainActivity.this, "Button pressed",
                Toast.LENGTH_SHORT).show();
    }

}
