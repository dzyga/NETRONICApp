package net.dzyga.android.netronicapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import net.dzyga.android.netronicapp.NApplication;
import net.dzyga.android.netronicapp.R;

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

    public static void showErrorNoInternet(Context context) {
        Toast.makeText(context, context.getResources().
                getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
    }

}