package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.multiactivitydemo.data.DataManager;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etData = (EditText) findViewById(R.id.etData);

        Button btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open DetailsActivity here
                Intent intentShowDetails = new Intent(
                        MainActivity.this,
                        DetailsActivity.class
                );
                intentShowDetails.putExtra(KEY_USERNAME,
                        etData.getText().toString());

                //intentShowDetails.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                DataManager.getInstance().setUserName(
                        etData.getText().toString());

                startActivity(intentShowDetails);

                //finish();
            }
        });
    }
}
