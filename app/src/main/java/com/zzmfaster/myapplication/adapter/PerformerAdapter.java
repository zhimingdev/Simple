package com.zzmfaster.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.bean.MovieBean;

public class PerformerAdapter extends BaseQuickAdapter<MovieBean.SubjectsBean.CastsBean,BaseViewHolder> {
    public PerformerAdapter() {
        super(R.layout.info_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieBean.SubjectsBean.CastsBean item) {
        helper.setText(R.id.tv_miaoshu,item.getName());
    }
}
