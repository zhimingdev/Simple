package com.zzmfaster.myapplication.ui.find.grils;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.bean.ResponseGrilsBean;
import com.zzmfaster.myapplication.custom.ScaleImageView;
import com.zzmfaster.myapplication.framework.BaseMvpFragment;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class GirlsFragment extends BaseMvpFragment implements GirlsContact.IVIEW {

    @BindView(R.id.rcv_girls)
    RecyclerView rcvGirls;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private GirlsPresenter mPresenter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private GirlsAdapter mGirlsAdapter;

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_girls;
    }

    @Override
    public void initView() {
        mPresenter = getPresenter(GirlsPresenter.class);
        mPresenter.getGirlsData();
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setAutoMeasureEnabled(true);
        rcvGirls.setLayoutManager(mStaggeredGridLayoutManager);
        rcvGirls.setHasFixedSize(true);
        mGirlsAdapter = new GirlsAdapter();
        rcvGirls.setAdapter(mGirlsAdapter);
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
    }

    @Override
    public void initData() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                },1500);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.getGirlsData();
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(false);
            }
        });
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void configPresenter() {
        creatPresenter(GirlsPresenter.class);
    }


    @Override
    public void refreshGirls(List<ResponseGrilsBean.ResultsBean> resultsBean) {
        mGirlsAdapter.setNewData(resultsBean);
    }

    public class GirlsAdapter extends BaseQuickAdapter<ResponseGrilsBean.ResultsBean, BaseViewHolder> {

        public GirlsAdapter() {
            super(R.layout.item_new_girls);
        }

        @Override
        protected void convert(BaseViewHolder helper, ResponseGrilsBean.ResultsBean item) {
            ScaleImageView imageView = (ScaleImageView) helper.getView(R.id.iv_girls);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (imageView.getHeight() == 0) {
                int height = 500 + new Random().nextInt(200);
                layoutParams.height = height;
            }
            imageView.requestLayout();
            Glide.with(mContext).load(item.getUrl()).into(imageView);
        }
    }
}
