package com.estm.GR_Location.services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.estm.GR_Location.SenderCurrentPosition;
import com.estm.GR_Location.commun.DatabaseHelper;
import com.estm.GR_Location.commun.MessageEvent;
import com.estm.GR_Location.data.LocationLog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class LocationTracker extends Service {
    String urlAddress ="http://192.168.42.12/GR_locat/public/Services/ChauffeurServices/CurrentPosition" ;
    public LocationTracker() {
    }

    public static final int notify = 300000 / 5;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling
    Context ctx;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new
        mTimer.scheduleAtFixedRate(new TimeDisplay(LocationTracker.this), 0, notify);   //Schedule task
        //final Context ctx = getApplicationContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
        Toast.makeText(this, "Service is Destroyed", Toast.LENGTH_SHORT).show();
    }

    //class TimeDisplay for handling task
    class TimeDisplay extends TimerTask {
        Context Ctx;

        public TimeDisplay(Context ctx) {
            Ctx = ctx;
        }

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // display toast

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new MyLocationListener();
                    if (ActivityCompat.checkSelfPermission(LocationTracker.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationTracker.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        //Toast.makeText(LocationTracker.this, "permission something", Toast.LENGTH_SHORT).show();
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                        //Toast.makeText(LocationTracker.this, "Service is running", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    //Toast.makeText(LocationTracker.this, "Service is running", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private class MyLocationListener implements LocationListener {

        private DatabaseHelper databaseHelper = null;

        @Override
        public void onLocationChanged(Location loc) {
           /* s = new SenderCurrentPosition(  urlAddress, _emailText, _passwordText);
            s.execute();*/
            String longitude = loc.getLongitude() + "";
            Log.v("Abdellatif", longitude);
            String latitude = loc.getLatitude() + "";
            Log.v("Abdellatif", latitude);
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: " + cityName;

            Log.w("Abdellatif", s);
            EventBus.getDefault().post(new MessageEvent(longitude, latitude));
            try {
                final Dao<LocationLog, Integer> locationLogDao = getHelper().getLocationLogDao();
                //locationLogDao.create(new LocationLog(loc.getLongitude() + "", loc.getLatitude() + ""));

            } catch (SQLException e) {
                Log.e("Abdellatif", e.getMessage());
            }
            //Toast.makeText(LocationTracker.this, s, Toast.LENGTH_SHORT).show();
            //editLocation.setText(s);
        }

        private DatabaseHelper getHelper() {
            if (databaseHelper == null) {
                databaseHelper = OpenHelperManager.getHelper(LocationTracker.this, DatabaseHelper.class);
            }
            return databaseHelper;
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

}
