package com.zzmfaster.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ToastUtils {

    /**
     * 自定义时间toast
     */
    public static void showToast(Context context,String text,int duration) {
        final Toast toast1 = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast1.show();
            }
        },0,100);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast1.cancel();
                timer.cancel();
            }
        },duration);
    }
}
