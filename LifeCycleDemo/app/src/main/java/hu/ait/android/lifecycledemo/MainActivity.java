package hu.ait.android.lifecycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_LOG = "TAG_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG_LOG, "onCreate called");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(MainActivity.this,
                "You will never exit.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG_LOG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LOG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LOG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LOG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LOG, "onDestroy called");
    }
    
}
