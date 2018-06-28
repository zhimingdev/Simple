package com.zzmfaster.myapplication.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;

public class ThreeLoginActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;

    @Override
    public int getLayoutId() {
        return R.layout.activity_three_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        UMShareAPI mShareAPI = UMShareAPI.get(mContext);
        UMAuthListener umAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(mContext, "成功了", Toast.LENGTH_SHORT).show();
                System.out.println(new Gson().toJson(map).toString()+"数据");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Toast.makeText(mContext,"失败了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Toast.makeText(mContext,"取消了",Toast.LENGTH_SHORT).show();
            }
        };
        tvQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI.getPlatformInfo(ThreeLoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
            }
        });

        tvWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI.getPlatformInfo(ThreeLoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(mContext).onActivityResult(requestCode,resultCode,data);
    }
}
