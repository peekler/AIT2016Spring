package hu.ait.android.sharedpreferencesdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_DATE = "PREF_DATE";
    public static final String KEY_FIRST_RUN_DATE = "KEY_FIRST_RUN_DATE";
    private TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = (TextView) findViewById(R.id.tvDate);

        saveFirstRunDate();

        loadFirstRunDate();
    }

    private void saveFirstRunDate() {
        SharedPreferences spFirstRunDate = getSharedPreferences(PREF_DATE,
                MODE_PRIVATE);

        if (!spFirstRunDate.contains(KEY_FIRST_RUN_DATE)) {
            SharedPreferences.Editor editor = spFirstRunDate.edit();
            editor.putString(KEY_FIRST_RUN_DATE,
                    new Date(System.currentTimeMillis()).toString());
            editor.commit();
        }
    }

    private void loadFirstRunDate() {
        SharedPreferences spFirstRunDate = getSharedPreferences(PREF_DATE,
                MODE_PRIVATE);
        String firstRunDate = spFirstRunDate.getString(
                KEY_FIRST_RUN_DATE, "Never");
        tvDate.setText(firstRunDate);
    }
}
