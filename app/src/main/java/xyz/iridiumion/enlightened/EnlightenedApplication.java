package xyz.iridiumion.enlightened;

import android.app.Application;
import android.os.StrictMode;

/**
 * Author: 0xFireball
 */
public class EnlightenedApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

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

