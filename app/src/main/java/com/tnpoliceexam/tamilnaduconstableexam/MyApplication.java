package com.tnpoliceexam.tamilnaduconstableexam;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tnpoliceexam.tamilnaduconstableexam.database.DatabaseHandler;
import com.tnpoliceexam.tamilnaduconstableexam.notification.ExampleNotificationOpenedHandler;
import com.tnpoliceexam.tamilnaduconstableexam.notification.ExampleNotificationReceivedHandler;
import com.onesignal.OneSignal;

/**
 * Created by AswinBalaji on 20-Oct-16.
 */
public class MyApplication extends Application {

    DatabaseHandler db1;
    String currentDateandTime;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler(this))
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }


}
