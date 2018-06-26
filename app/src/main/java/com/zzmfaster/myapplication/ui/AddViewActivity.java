package com.zzmfaster.myapplication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class AddViewActivity extends BaseActivity {
    @BindView(R.id.tv_recycl)
    RecyclerView tvRecycl;
    private String[] arrs ={"骚猪", "朱哥", "猪猪侠"};
    private String[] arrs1 = {"骚猪之诞生", "骚猪之龙头灞街", "骚猪之大战龙头霸街扛把子"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_addview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        List<String> strings = Arrays.asList(arrs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tvRecycl.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        tvRecycl.setAdapter(myAdapter);
        myAdapter.setNewData(strings);
    }

    @Override
    public void initData() {

    }

    private class MyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.addview_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_one,item);
            ViewGroup view = (ViewGroup) helper.getView(R.id.ll);
            view.removeAllViews();
            for (int i = 0;i<arrs1.length;i++) {
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.addview_one_item,null);
                TextView textView = (TextView) view1.findViewById(R.id.tv_child_text);
                textView.setText(arrs1[i]);
                view.addView(view1);
            }
        }
    }

}
