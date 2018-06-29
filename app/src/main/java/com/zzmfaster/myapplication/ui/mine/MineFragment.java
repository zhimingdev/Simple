package com.zzmfaster.myapplication.ui.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.custom.titleview.TitleView;
import com.zzmfaster.myapplication.ui.AddViewActivity;
import com.zzmfaster.myapplication.ui.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.rl_test)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.rl_test_one)
    RelativeLayout mRelativeLayoutOne;
    private String mS;

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
                mS = ((MineAdapter) adapter).getItem(position);
                System.out.println("点击的是"+mS);
                mineAdapter.notifyDataSetChanged();
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

        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AddViewActivity.class);
            }
        });
        mRelativeLayoutOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ThreeLoginActivity.class);
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
            if (item.equals(mS)){
                helper.getView(R.id.tv_time).setBackground(getResources().getDrawable(R.drawable.radiobutton_selected_shape));
                return;
            }
            helper.getView(R.id.tv_time).setBackground(getResources().getDrawable(R.drawable.radiobutton_unselected_shape));
        }
    }

}
