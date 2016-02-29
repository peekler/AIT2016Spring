package hu.ait.android.multiactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hu.ait.android.multiactivitydemo.data.DataManager;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvUserName = (TextView) findViewById(R.id.tvUserName);

        /*if (getIntent().hasExtra(MainActivity.KEY_USERNAME)) {
            tvUserName.setText(getIntent().getStringExtra(
                    MainActivity.KEY_USERNAME));
        }*/

        tvUserName.setText(DataManager.getInstance().getUserName());

    }
}
