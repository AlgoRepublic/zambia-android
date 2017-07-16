package com.algorelpublic.zambia;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.algorelpublic.zambia.utils.TinyDB;
import com.algorelpublic.zambia.utils.Util;

/**
 * Created by Adil on 6/6/2017.
 */

public class Zambia extends Application {
    public SharedPreferences appSharedPrefs;
    public SharedPreferences.Editor prefsEditor;
    public String SHARED_NAME = "com.algorepublic.Zambia";
    public static TinyDB db;
    private static Zambia instance;
    public static Context context;
    private static final String TAG = Zambia.class.getName();

    //
    public static Zambia getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.appSharedPrefs = getSharedPreferences(SHARED_NAME,
                Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        db = new TinyDB(getApplicationContext());
        if (Util.LOG_ENABLED) {
            Log.v(TAG, "public void onCreate()");
        }
        Zambia.context = getApplicationContext();

    }

    /**
     *
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Log.i(TAG, "Freeing memory ...");

    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (Util.LOG_ENABLED) {
            Log.v(TAG, "public void onTrimMemory (int level)");
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

