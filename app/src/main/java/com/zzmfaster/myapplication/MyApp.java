package com.zzmfaster.myapplication;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.mob.MobSDK;

public class MyApp extends MultiDexApplication {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MobSDK.init(this);
        MultiDex.install(this);
    }

    public static Application getInstance(){
        return instance;
    }
}
