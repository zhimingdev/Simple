package com.zzmfaster.myapplication.ui.mine;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;
import com.zzmfaster.myapplication.bean.User;
import com.zzmfaster.myapplication.databinding.ActivityMsgBinding;

public class MsgActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ActivityMsgBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_msg);
        User user = new User("test", "user");
        viewDataBinding.setUser(user);
    }

    @Override
    public void initData() {

    }

}
