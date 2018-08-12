package com.ashok.countryinfo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ashok on 11-08-2018.
 *
 * This provides helper methods to help UI.
 */

public class AppUtils {

    /**
     *
     * @param context app context
     * @return internet availability status
     *
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
