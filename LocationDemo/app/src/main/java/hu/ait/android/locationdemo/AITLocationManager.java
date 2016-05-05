package hu.ait.android.locationdemo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

public class AITLocationManager implements LocationListener {

    private Context context;
    private LocationManager locationManager;

    public AITLocationManager(Context context) {
        this.context = context;
    }

    public void startLocationMonitoring() throws SecurityException {
        locationManager =
                (LocationManager) context.getSystemService(
                        Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0,
                this
        );
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0,
                this
        );
    }

    public void stopLocationMonitoring() throws SecurityException {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        /*Toast.makeText(context,
                "Location: "+location.getLatitude()+"," +
                        location.getLongitude(),
                Toast.LENGTH_SHORT).show();*/


        EventBus.getDefault().post(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
