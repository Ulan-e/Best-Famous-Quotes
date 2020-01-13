package com.ulan.app.quotes.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class can check internet connection
 */

public class InternetConnectionHelpers {

    // get internet connection status
    public static String getConnectionStatusString(Context context){
        String status = "";
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                status = "Wi-Fi enabled";
                return status;
            }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                status = "Mobile data enabled";
                return status;
            }
        }else{
            status = "No internet is available";
            return status;
        }
        return status;
    }
}
