package net.dzyga.android.netronicapp;

import android.app.Application;
import android.content.Context;

public class NApplication extends Application {

    private static NApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }


}
