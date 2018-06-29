package com.zzmfaster.myapplication.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.framework.BaseMvpActivity;
import com.zzmfaster.myapplication.framework.common.CommonPresenter;
import com.zzmfaster.myapplication.framework.common.ICommonContract;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class Aativity extends BaseMvpActivity implements ICommonContract.IView {
    @BindView(R.id.tv_share_youmeng)
    TextView tvShareYoumeng;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;

    @Override
    public int getlayoutId() {
        return R.layout.aast_one;
    }

    @Override
    public void initView() {
        CommonPresenter presenter = (CommonPresenter) getPresenter(CommonPresenter.class);
        presenter.requestData();
        mShareListener = new CustomShareListener(this);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(Aativity.this).setDisplayList(
                SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN_FAVORITE,SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QZONE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        UMWeb web = new UMWeb("http://mobile.umeng.com/social");
                        web.setTitle("来自分享面板标题");
                        web.setDescription("来自分享面板内容");
                        web.setThumb(new UMImage(Aativity.this, R.drawable.ic_share_qq));
                        new ShareAction(Aativity.this).withMedia(web)
                                .setPlatform(share_media)
                                .setCallback(mShareListener)
                                .share();
                    }
                });

        tvShareYoumeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mShareAction.open();
            }
       });

        //异步方法
        new AsyncTask<Long,String,Void>(){
            @Override
            protected Void doInBackground(Long... longs) {
                tvShareYoumeng.setText("测试友盟分享");
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(String...values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    @Override
    public void configPresenter() {
        creatPresenter(CommonPresenter.class);
    }

    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvShareYoumeng.setText("测试计时技术后的文字");
            }
        },2000);
    }

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<Aativity> mActivity;

        private CustomShareListener(Aativity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }


}
