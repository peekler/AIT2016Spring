package hu.bme.android.threadandtimerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    private TextView tvData;

    private int counter = 0;
    private boolean enabled = true;

    

    private Timer mainTimer;

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvData.append("#");
                }
            });
        }
    }


    private class CounterThread extends Thread {
        @Override
        public void run() {
            while (enabled) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvCounter.setText(String.valueOf(counter++));
                    }
                });

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = (TextView) findViewById(R.id.tvCounter);
        tvData = (TextView) findViewById(R.id.tvData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new CounterThread().start();

        mainTimer = new Timer();
        mainTimer.schedule(new MyTimerTask(),4000, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        enabled = false;

        mainTimer.cancel();
    }
}
