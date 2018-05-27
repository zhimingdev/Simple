package com.zzmfaster.myapplication.ui.mine;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;


public class SkidRightTwoActivity extends BaseActivity {
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_gif)
    ImageView imgGif;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    private int mImgPath;

    @Override
    public int getLayoutId() {
        return R.layout.activity_skid_2;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mImgPath = getIntent().getIntExtra("img", R.drawable.skid_right_3);
            String title = getIntent().getStringExtra("title");
            tvTitle.setText(title);
            Glide.with(this).load(mImgPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgBg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Glide.with(SkidRightTwoActivity.this).load(mImgPath).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgGif);
                }
            }, 1000);

        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void onBackPressed() {
        imgGif.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }
}
