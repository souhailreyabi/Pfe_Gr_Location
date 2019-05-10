package com.estm.GR_Location.commun;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


/**
 * Created by AbdellatifMANNOUCHE on 4/11/16.
 */

public class utils {

    public static boolean hasPermission(Context context, String permission) {
        if(permission.equalsIgnoreCase("android.permission.RECEIVE_SMS") || permission.equalsIgnoreCase("android.permission.ACCESS_FINE_LOCATION")){
            //Timber.w("this is  : " + permission.toString());
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permission != null) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                //Timber.w("Non granted : " + permission.toString());
                return false;
            }
        }

        return true;
    }

}
