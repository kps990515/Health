package org.andriodtown.health;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 2018-02-04.
 */

public class ApplicationInitializer extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationInitializer.context = getApplicationContext();
        SharedPreferenceManager.getInstance();
    }

    public static Context getAppContext(){
        return ApplicationInitializer.context;
    }
}
