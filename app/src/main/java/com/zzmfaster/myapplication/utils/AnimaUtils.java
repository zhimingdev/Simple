package com.zzmfaster.myapplication.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class AnimaUtils {
    public static void openAnima(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setDuration(800);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(lir);
        view.startAnimation(animationSet);
    }

    public static void closeAnima(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setDuration(800);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(lir);
        view.startAnimation(animationSet);
    }
}
