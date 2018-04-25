package com.zzmfaster.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                finish();
            }
        },1500);

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
