package net.dzyga.android.netronicapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.dzyga.android.netronicapp.NApplication;

public class ConnectivityUtils {

    public static boolean isConnected() {
        ConnectivityManager connectivityManager =  (ConnectivityManager) NApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn =  networkInfo == null ? false : networkInfo.isConnected();
        networkInfo =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo == null ? false : networkInfo.isConnected();
        if (isWifiConn || isMobileConn) return true;
        return false;
    }
}