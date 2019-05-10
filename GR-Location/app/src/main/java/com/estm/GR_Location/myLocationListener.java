package com.estm.GR_Location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abdellatif on 08/04/2017.
 */

public class myLocationListener implements LocationListener{
    Context context;
    public myLocationListener(Context context) {

        this.context = context;

    }


    @Override
    public void onLocationChanged(Location location) {

      /*  String Loca = "Log: " + String.valueOf(location.getLongitude()) +
                    ", Lat: " + String.valueOf(location.getLatitude()) ;
*/
        /*writeInFile(String.valueOf(location.getLatitude()) , String.valueOf(location.getLongitude()));
        // Set the Map from here
        Map<Double,Double> map;
        map =readFromFile() ;
        Toast.makeText(context,"From File :"+map.toString(),Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Toast.makeText(context,"GPS stutus is changed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context,"GPS is Enabled",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context,"GPS is Disabled",Toast.LENGTH_LONG).show();

    }

    public void writeInFile(String lat,String log){
        String line ;
        try{
            String file_name = "locations" ;
            FileOutputStream fileOutputStream = context.openFileOutput(file_name,context.MODE_PRIVATE);
            line = lat +"-" + log + "\n\r";
            fileOutputStream.write(line.getBytes());
            fileOutputStream.close() ;
         //   Toast.makeText(context.getApplicationContext(),"File Saved",Toast.LENGTH_LONG).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Map<Double,Double> readFromFile(){
        Map<Double,Double> map = new HashMap<Double,Double>() ;
        String file_name = "locations" ;
        String data ;
      try {
          FileInputStream fileInputStream = context.openFileInput(file_name);
          InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         // StringBuffer stringBuffer = new StringBuffer();
          while ((data=bufferedReader.readLine()) != null){
           // stringBuffer.append(data + "\n");
              //String string = "004-034556";
              String[] parts = data.split("-");
              String part1 = parts[0]; // 004
              String part2 = parts[1]; // 034556
              double lat = Double.parseDouble(part1);
              double log = Double.parseDouble(part2);
              Toast.makeText(context,data,Toast.LENGTH_LONG).show();
              map.put(lat,log) ;
          }
        fileInputStream.close();
          bufferedReader.close();
      }catch(Exception e){
          e.printStackTrace();
      }

    return map;
    }
}
