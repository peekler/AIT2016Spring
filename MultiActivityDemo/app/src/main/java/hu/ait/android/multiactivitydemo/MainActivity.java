package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to DetailsActivity
                Intent intentShowDetails = new Intent(
                        MainActivity.this,
                        DetailsActivity.class
                );
                //intentShowDetails.setFlags(
                //        Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intentShowDetails);

                finish();
            }
        });

    }
}
