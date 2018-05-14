package com.zzmfaster.myapplication.ui.decoration;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;

public class TestPhotoActivity extends BaseActivity {
    @BindView(R.id.iv_pv)
    PhotoView ivPv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_photoview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(ivPv);
    }

    @Override
    public void initData() {

    }
}
