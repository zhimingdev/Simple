package com.zzmfaster.myapplication.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dingmouren.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;

public class SkidRightActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SkidRightLayoutManager mSkidRightLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_skid_1;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mSkidRightLayoutManager = new SkidRightLayoutManager(1.5f, 0.85f);
        recyclerView.setLayoutManager(mSkidRightLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int[] imgs = {
                R.drawable.skid_right_1,
                R.drawable.skid_right_2,
                R.drawable.skid_right_3,
                R.drawable.skid_right_4,
                R.drawable.skid_right_5,
                R.drawable.skid_right_6,
                R.drawable.skid_right_7,

        };
        String[] titles = {"Acknowl", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence"};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_skid_right_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Glide.with(mContext).load(imgs[position % 7]).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imgBg);
            holder.tvTitle.setText(titles[position % 6]);
            holder.imgBg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SkidRightActivity.this, SkidRightTwoActivity.class);
                    intent.putExtra("img", imgs[position % 7]);
                    intent.putExtra("title", titles[position % 6]);
                    Pair<View, String> p1 = Pair.create((View) holder.imgBg, "img_view_1");
                    Pair<View, String> p2 = Pair.create((View) holder.tvTitle, "title_1");
                    Pair<View, String> p3 = Pair.create((View) holder.tvBottom, "tv_bottom");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(SkidRightActivity.this, p1, p2, p3);
                    startActivity(intent, options.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            TextView tvTitle;
            TextView tvBottom;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvBottom = itemView.findViewById(R.id.tv_bottom);
            }
        }
    }
}
