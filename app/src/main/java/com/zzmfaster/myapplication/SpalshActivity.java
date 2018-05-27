package com.zzmfaster.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ObjectUtils;
import com.zzmfaster.myapplication.core.FingerprintCore;
import com.zzmfaster.myapplication.ui.FingerprintActivity;

public class SpalshActivity extends AppCompatActivity {
    private FingerprintCore mFingerprintCore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        SharedPreferences sharedPreferences = this.getSharedPreferences("pass", Context.MODE_PRIVATE);
        String pas = sharedPreferences.getString("pas", "");
        mFingerprintCore = new FingerprintCore(this);
        if (mFingerprintCore.isSupport()) {//支持指纹
            if (mFingerprintCore.isHasEnrolledFingerprints()) {
                //有录入指纹
                startActivity(new Intent(SpalshActivity.this, FingerprintActivity.class));
                finish();
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SpalshActivity.this,GuideActivity.class));
                        finish();
                    }
                },1500);
            }
        }else {     //不支持指纹
            if (ObjectUtils.isEmpty(pas)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SpalshActivity.this,GuideActivity.class));
                        finish();
                    }
                },1500);
            }else {
                startActivity(new Intent(SpalshActivity.this,ClocokActivity.class));
                this.finish();
            }
        }

//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions
//                .request(Manifest.permission.ACCESS_FINE_LOCATION)
//                .subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if(aBoolean) {
//                    //允许权限
//                }else {
//                    //拒绝权限
//                }
//            }
//        });
    }
}
