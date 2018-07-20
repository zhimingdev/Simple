package com.zzmfaster.myapplication.ui.test;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.zzmfaster.myapplication.MainActivity;
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
    private boolean mFrom;


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
    protected void onResume() {
        super.onResume();
        mFrom = getIntent().getBooleanExtra("from", false);
        System.out.println("答应的"+mFrom);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(mFrom){
                Intent intent=new Intent(TestActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);

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
