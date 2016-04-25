package hu.ait.android.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirPlaneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "AIRPLANE MODE HAS BEEN CHANGED!", Toast.LENGTH_SHORT).show();

    }

}
