package xyz.iridiumion.enlightened;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;

/**
 * Author: 0xFireball
 */
public class EnlightenedApplication extends Application {
    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences loadedPrefs = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        preferences = loadedPrefs;
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());

            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects()
                            .penaltyLog()
                            .penaltyDeath()
                            .build());
        }
    }
}

