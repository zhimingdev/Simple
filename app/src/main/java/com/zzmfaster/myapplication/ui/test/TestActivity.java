package com.zzmfaster.myapplication.ui.test;

import android.view.View;
import android.widget.TextView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.framework.BaseMvpActivity;
import com.zzmfaster.myapplication.framework.common.CommonPresenter;
import com.zzmfaster.myapplication.framework.common.ICommonContract;

import butterknife.BindView;

public class TestActivity extends BaseMvpActivity<TestPresenter> implements TestContract.IView ,ICommonContract.IView {
    @BindView(R.id.tv_test)
    TextView tvTest;


    @Override
    public int getlayoutId() {
        return R.layout.layout;
    }

    @Override
    public void initView() {
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPresenter presenter = getPresenter(TestPresenter.class);
                presenter.refreshText();

                CommonPresenter commonPresenter = getPresenter(CommonPresenter.class);
                commonPresenter.requestData();
                showLoadingDialog();
            }
        });
    }


    @Override
    public void refreshText() {
        tvTest.setText("变化后的文本");
    }




    @Override
    public void configPresenter() {
        creatPresenter(TestPresenter.class, CommonPresenter.class);
    }


    @Override
    public void refreshData() {
        tvTest.setBackgroundColor(getResources().getColor(R.color.laser_color));
    }
}
