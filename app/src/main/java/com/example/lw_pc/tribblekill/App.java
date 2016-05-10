package com.example.lw_pc.tribblekill;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by LW-PC on 2016/5/6.
 */
public class App extends Application {
    public SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
    }
}
