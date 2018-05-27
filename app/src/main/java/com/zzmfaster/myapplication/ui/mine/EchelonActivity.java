package com.zzmfaster.myapplication.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseActivity;

import butterknife.BindView;

public class EchelonActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private EchelonLayoutManager mLayoutManager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_echelon;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mLayoutManager = new EchelonLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private int[] icons = {R.drawable.header_icon_1,R.drawable.header_icon_2,R.drawable.header_icon_3,R.drawable.header_icon_4};
        private int[] bgs = {R.drawable.bg_1,R.drawable.bg_2,R.drawable.bg_3,R.drawable.bg_4};
        private String[] nickNames = {"左耳近心","凉雨初夏","稚久九栀","半窗疏影"};
        private String[] descs = {
                "回不去的地方叫故乡 没有根的迁徙叫流浪...",
                "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口",
                "原来地久天长，只是误会一场",
                "不是故事的结局不够好，而是我们对故事的要求过多",
                "只想优雅转身，不料华丽撞墙"
        };
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelon,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.icon.setImageResource(icons[position%4]);
            holder.nickName.setText(nickNames[position%4]);
            holder.desc.setText(descs[position%5]);
            holder.bg.setImageResource(bgs[position%4]);
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            ImageView bg;
            TextView nickName;
            TextView desc;
            public ViewHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.img_icon);
                bg = itemView.findViewById(R.id.img_bg);
                nickName = itemView.findViewById(R.id.tv_nickname);
                desc = itemView.findViewById(R.id.tv_desc);

            }
        }
    }

}
