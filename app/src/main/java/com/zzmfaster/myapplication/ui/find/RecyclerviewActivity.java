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

public class RecyclerviewActivity extends BaseActivity {
    @BindView(R.id.tb_tablayout_recyclerview)
    TabLayout mTablayout;
    @BindView(R.id.rv_recyclerview)
    RecyclerView mRecyclerView;

    private List<RecyclerBean> mDatalist = new ArrayList<>();
    private boolean isClick = false;

    @Override
    public int getLayoutId() {
        return R.layout.aa;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        for (int i = 0; i < 3; i++) {
            RecyclerBean bean = new RecyclerBean("content" + i);
            mDatalist.add(bean);
        }
        MyAdapter mAdapter = new MyAdapter(RecyclerviewActivity.this, mDatalist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        for (int i = 0; i < mDatalist.size(); i++) {
            TabLayout.Tab tab = mTablayout.newTab();
            tab.setText(mDatalist.get(i).context);
            mTablayout.addTab(tab);
        }
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    isClick = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isClick) {
                    LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstPosition = l.findFirstVisibleItemPosition();
                    mTablayout.setScrollPosition(firstPosition, 0f, true);
                }
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isClick = true;
                int position = tab.getPosition();
                LinearLayoutManager l = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int firstPosition = l.findFirstVisibleItemPosition();
                int lastPosition = l.findLastVisibleItemPosition();
                if (position > lastPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else if (position < firstPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else {
                    int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    mRecyclerView.smoothScrollBy(0, top);
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


    private class MyAdapter extends BaseQuickAdapter<RecyclerBean,BaseViewHolder>{

        public MyAdapter(RecyclerviewActivity recyclerviewActivity, List<RecyclerBean> datalist) {
            super(R.layout.ab,datalist);
        }

        @Override
        protected void convert(BaseViewHolder helper, RecyclerBean item) {
            helper.setText(R.id.tv_item_recycler_content,item.context);
        }
    }

}
