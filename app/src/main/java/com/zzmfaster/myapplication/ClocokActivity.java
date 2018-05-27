package com.zzmfaster.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import com.nightonke.blurlockview.BlurLockView;
import com.nightonke.blurlockview.Password;
import com.zzmfaster.myapplication.base.BaseActivity;
import com.zzmfaster.myapplication.utils.ToastUtils;

import butterknife.BindView;

public class ClocokActivity extends BaseActivity implements BlurLockView.OnPasswordInputListener {
    @BindView(R.id.blurlockview)
    BlurLockView blurlockview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_passclock;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("pass", Context.MODE_PRIVATE);
        String pas = sharedPreferences.getString("pas", "");
        blurlockview.setCorrectPassword(pas);
        blurlockview.setTitle("请输入密码");
        blurlockview.setTypeface(Typeface.DEFAULT);
        blurlockview.setType(Password.NUMBER, false);
        blurlockview.setOnPasswordInputListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void correct(String inputPassword) {
        this.finish();
        startActivity(GuideActivity.class);
    }

    @Override
    public void incorrect(String inputPassword) {
        ToastUtils.showToast(mContext,"密码错误,请重新输入",1500);
    }

    @Override
    public void input(String inputPassword) {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }
}
