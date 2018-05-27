package com.zzmfaster.myapplication.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.bean.GrilBean;

public class MsgAdapter extends BaseQuickAdapter<GrilBean,BaseViewHolder> {

    public MsgAdapter() {
        super(R.layout.item_grils);
    }

    @Override
    protected void convert(BaseViewHolder helper, GrilBean item) {
        helper.setText(R.id.tv_msg_one,item.getTitle());
        helper.setText(R.id.tv_msg_two,item.getIntroduction());
        helper.setText(R.id.tv_msg_three,item.getOccupation());
        helper.setText(R.id.tv_msg_four,item.getUserName());
        ImageView imageView = (ImageView) helper.getView(R.id.iv_left_icon);
        Glide.with(mContext).load(item.getImg()).into(imageView);
    }
}
