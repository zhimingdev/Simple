package com.zzmfaster.myapplication.ui.find;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;
import com.zzmfaster.myapplication.bean.RecyclerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomRecyclerviewActivity extends BaseActivity {
    @BindView(R.id.tb_tablayout_recyclerview)
    TabLayout tbTablayoutRecyclerview;
    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRecyclerview;
    private List<RecyclerBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.aa;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        for (int i = 0;i<3;i++) {
            RecyclerBean recyclerBean = new RecyclerBean("Tab"+i);
            mList.add(recyclerBean);
        }
        MyAdapter myAdapter = new MyAdapter(CustomRecyclerviewActivity.this,mList);
        rvRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        rvRecyclerview.setAdapter(myAdapter);
        for (int i =0;i<mList.size();i++) {
            TabLayout.Tab tabLayout = tbTablayoutRecyclerview.newTab();
            tabLayout.setText(mList.get(i).context);
            tbTablayoutRecyclerview.addTab(tabLayout);
        }
        tbTablayoutRecyclerview.setTabMode(TabLayout.MODE_FIXED);

        tbTablayoutRecyclerview.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                LinearLayoutManager layoutManager = (LinearLayoutManager)rvRecyclerview.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (position >firstVisibleItemPosition) {
                    rvRecyclerview.smoothScrollToPosition(position);
                }else if (position < lastVisibleItemPosition) {
                    rvRecyclerview.smoothScrollToPosition(position);
                }else {
                    int top = rvRecyclerview.getChildAt(position - firstVisibleItemPosition).getTop();
                    rvRecyclerview.smoothScrollBy(0,top);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void initData() {

    }

    private class MyAdapter extends BaseQuickAdapter<RecyclerBean,BaseViewHolder> {

        public MyAdapter(CustomRecyclerviewActivity recyclerviewActivity, List<RecyclerBean> datalist) {
            super(R.layout.ab,datalist);
        }

        @Override
        protected void convert(BaseViewHolder helper, RecyclerBean item) {
            helper.setText(R.id.tv_item_recycler_content,item.context);
        }
    }

}
