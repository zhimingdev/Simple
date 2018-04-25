package com.zzmfaster.myapplication;

import android.app.Application;

import com.mob.MobSDK;

public class MyApp extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MobSDK.init(this);
    }

    public static Application getInstance(){
        return instance;
    }
}
