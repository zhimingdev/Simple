package com.zzmfaster.myapplication.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class ScreenUtils {
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(Context context, float bgAlpha) {
        WindowManager.LayoutParams lp = ((AppCompatActivity)context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((AppCompatActivity)context).getWindow().setAttributes(lp);
    }
}
