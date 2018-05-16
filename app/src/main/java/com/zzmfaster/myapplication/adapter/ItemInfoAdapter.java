package com.zzmfaster.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;

public class ItemInfoAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ItemInfoAdapter() {
        super(R.layout.info_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_miaoshu,item);
    }
}
