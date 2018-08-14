package com.zzmfaster.myapplication.ui.mine;

import android.graphics.Typeface;
import android.os.Bundle;

import com.nightonke.blurlockview.BlurLockView;
import com.nightonke.blurlockview.Password;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;

public class PassClockActivity extends BaseActivity implements BlurLockView.OnLeftButtonClickListener {
    @BindView(R.id.blurlockview)
    BlurLockView blurlockview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_passclock;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        blurlockview.setTitle("请输入密码");
        blurlockview.setLeftButton("返回");
        blurlockview.setRightButton("删除");
        blurlockview.setPasswordLength(4);
        blurlockview.setTypeface(Typeface.DEFAULT);
        blurlockview.setType(Password.NUMBER, false);
        blurlockview.setOnLeftButtonClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick() {
        this.finish();
    }
}
