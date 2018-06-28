package com.zzmfaster.myapplication.ui.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.adapter.MsgAdapter;
import com.zzmfaster.myapplication.bean.GrilBean;
import com.zzmfaster.myapplication.framework.BaseMvpActivity;
import com.zzmfaster.myapplication.framework.common.CommonPresenter;
import com.zzmfaster.myapplication.framework.common.ICommonContract;

import java.util.List;

import butterknife.BindView;

public class TestActivity extends BaseMvpActivity<TestPresenter> implements TestContract.IView ,ICommonContract.IView {
    @BindView(R.id.test_rcv)
    RecyclerView testRcv;
    private TestPresenter presenter;
    private MsgAdapter msgAdapter;


    @Override
    public int getlayoutId() {
        return R.layout.layout;
    }

    @Override
    public void initView() {
        presenter = getPresenter(TestPresenter.class);
        this.presenter.getDatas();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testRcv.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter();
        testRcv.setAdapter(msgAdapter);
    }



    @Override
    public void configPresenter() {
        creatPresenter(TestPresenter.class, CommonPresenter.class);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshText(List<GrilBean> list) {
        msgAdapter.setNewData(list);
    }
}
