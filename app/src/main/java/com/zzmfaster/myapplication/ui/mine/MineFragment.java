package com.zzmfaster.myapplication.ui.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.custom.titleview.TitleView;
import com.zzmfaster.myapplication.ui.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {

    @BindView(R.id.title_mine)
    TitleView titleMine;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.rcv_mine)
    RecyclerView rcvMine;

    List<String> list = new ArrayList<>();
    @BindView(R.id.rl_pass_clock)
    RelativeLayout rlPassClock;
    @BindView(R.id.rl_groupstyle)
    RelativeLayout rlGroupstyle;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        list.add("30天");
        list.add("90天");
        list.add("120天");
        list.add("1年");
        list.add("2年");
        list.add("3年");
        titleMine.getLeftBackImageTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(MsgActivity.class);
            }
        });

        titleMine.getRightImageIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(TestActivity.class);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        rcvMine.setLayoutManager(gridLayoutManager);
        MineAdapter mineAdapter = new MineAdapter();
        rcvMine.setAdapter(mineAdapter);
        mineAdapter.setNewData(list);
        mineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                View view1 = gridLayoutManager.findViewByPosition(position);
                view1.findViewById(R.id.tv_time).setBackgroundResource(R.drawable.radiobutton_selected_shape);
                int position1 = SPUtils.getInstance().getInt("position", -1);
                if (!(position == position1)) {
                    View view2 = gridLayoutManager.findViewByPosition(position1);
                    view2.findViewById(R.id.tv_time).setBackgroundResource(R.drawable.radiobutton_unselected_shape);
                }
                SPUtils.getInstance().put("position", position);
            }
        });
    }

    @Override
    public void initData() {
        rlPassClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(PassClockActivity.class);
            }
        });

        rlGroupstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(GroupActivity.class);
            }
        });
    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    public class MineAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MineAdapter() {
            super(R.layout.item_mine);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_time, item);

        }
    }

}
