package com.zzmfaster.myapplication.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.bean.MovieBean;

public class MovieAdapter extends BaseQuickAdapter<MovieBean.SubjectsBean,BaseViewHolder> {

    public MovieAdapter() {
        super(R.layout.item_movie);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieBean.SubjectsBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        ImageView imageView = (ImageView) helper.getView(R.id.iv_image);
        Glide.with(mContext).load(item.getImages().getSmall()).into(imageView);
        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,item.getGenres().size());
        recyclerView.setLayoutManager(gridLayoutManager);
        ItemInfoAdapter itemInfoAdapter = new ItemInfoAdapter();
        itemInfoAdapter.setNewData(item.getGenres());
        recyclerView.setAdapter(itemInfoAdapter);

        RecyclerView recyclerView1 = (RecyclerView) helper.getView(R.id.recyclerview_yanyuan);
        GridLayoutManager manager = new GridLayoutManager(mContext,item.getCasts().size());
        recyclerView.setLayoutManager(manager);
        PerformerAdapter performerAdapter = new PerformerAdapter();
        performerAdapter.setNewData(item.getCasts());
        recyclerView1.setAdapter(performerAdapter);

        helper.setText(R.id.tv_time,item.getYear());
        helper.setText(R.id.tv_director,item.getDirectors().get(0).getName());
    }
}
