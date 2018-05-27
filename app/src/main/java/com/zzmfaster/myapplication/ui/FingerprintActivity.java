package com.zzmfaster.myapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zzmfaster.myapplication.MainActivity;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;
import com.zzmfaster.myapplication.core.FingerprintCore;
import com.zzmfaster.myapplication.utils.ToastUtils;

import butterknife.BindView;

public class FingerprintActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    TextView[] tvarrs = new TextView[5];
    private int postion = 0;
    private FingerprintCore mFingerprintCore;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fingerprint;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvarrs[0] = tv1;
        tvarrs[1] = tv2;
        tvarrs[2] = tv3;
        tvarrs[3] = tv4;
        tvarrs[4] = tv5;
        postion = 0;
        handler.sendEmptyMessageDelayed(0,300);
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mFingerprintCore.startAuthenticate();
    }

    @Override
    public void initData() {

    }

    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            startActivity(MainActivity.class);
            finish();
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            ToastUtils.showToast(mContext,"指纹识别失败,请重试",1500);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                int i = postion % 5;
                if (i == 0){
                    tvarrs[4].setBackground(null);
                    tvarrs[i].setBackgroundColor(getResources().getColor(R.color.thin_blue));
                }
                else{
                    tvarrs[i].setBackgroundColor(getResources().getColor(R.color.thin_blue));
                    tvarrs[i-1].setBackground(null);
                }
                postion++;
                handler.sendEmptyMessageDelayed(0,300);
            }
        }
    };

}
