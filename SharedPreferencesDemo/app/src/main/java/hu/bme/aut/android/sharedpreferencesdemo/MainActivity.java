package hu.bme.aut.android.sharedpreferencesdemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_TIME = "PREF_TIME";
    public static final String KEY_START_TIME = "KEY_START_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String lastStartMsg = getLastStartTime();
        Toast.makeText(this, lastStartMsg, Toast.LENGTH_SHORT).show();

        saveStartTime();
    }

    private void saveStartTime() {
        SharedPreferences sp = getSharedPreferences(
                PREF_TIME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(KEY_START_TIME, System.currentTimeMillis());
        editor.commit();
    }

    private String getLastStartTime() {
        SharedPreferences sp = getSharedPreferences(
                PREF_TIME, MODE_PRIVATE);
        long time = sp.getLong(KEY_START_TIME, -1);

        return (time != -1) ? new Date(time).toString() :
                "this is the first time when you started the app";
    }

}
