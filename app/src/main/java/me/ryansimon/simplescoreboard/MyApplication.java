package me.ryansimon.simplescoreboard;

import android.app.Application;
import android.content.Context;

/**
 * @author Ryan Simon
 *
 * Used to access global application info such as app context for grabbing resources
 */
public final class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
