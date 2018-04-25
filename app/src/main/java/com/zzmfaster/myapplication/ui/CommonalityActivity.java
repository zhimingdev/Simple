package com.zzmfaster.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
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
        //设置用自己的浏览器打开
        wvContent.loadUrl(result);
        wvContent.setWebViewClient(new MyWebViewClient());
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
    }
    //预防内存泄露
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
