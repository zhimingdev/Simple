package com.zzmfaster.myapplication.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CommonalityActivity extends BaseActivity {
    @BindView(R.id.ib_close)
    ImageButton ibClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_share)
    ImageButton ibShare;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commonality;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");

        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setSupportZoom(true);  //支持缩放
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存
        //设置用自己的浏览器打开
        wvContent.loadUrl(result);
        wvContent.setWebViewClient(new MyWebViewClient());
        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressbar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ib_close, R.id.ib_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_close:
                webViewGoBack();
                break;
            case R.id.ib_share:
                break;
        }
    }

    public void webViewGoBack() {
        if (wvContent.canGoBack()) {
            wvContent.goBack();
        } else {
            this.finish();
        }
    }


    //自定义浏览器
    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressbar.setVisibility(View.GONE);
        }
    }

    //预防内存泄露
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
