package com.zzmfaster.myapplication.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class GroupActivity extends BaseActivity {
    @BindView(R.id.tv_style_one)
    TextView tvStyleOne;
    @BindView(R.id.tv_style_two)
    TextView tvStyleTwo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_groupstyle;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_style_one, R.id.tv_style_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_style_one:
                startActivity(EchelonActivity.class);
                break;
            case R.id.tv_style_two:
                startActivity(SkidRightActivity.class);
                break;
        }
    }
}
