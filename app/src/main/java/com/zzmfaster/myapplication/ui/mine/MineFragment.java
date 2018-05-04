package com.zzmfaster.myapplication.ui.mine;

import android.view.View;
import android.widget.ImageView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.custom.titleview.TitleView;
import com.zzmfaster.myapplication.ui.test.TestActivity;
import com.zzmfaster.myapplication.utils.ToastUtils;

import butterknife.BindView;

public class MineFragment extends BaseFragment {

    @BindView(R.id.title_mine)
    TitleView titleMine;
    @BindView(R.id.iv_mine)
    ImageView ivMine;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        titleMine.getLeftBackImageTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ToastUtils.showToast(mActivity,"被点击了",1500);
            }
        });

        titleMine.getRightImageIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(TestActivity.class);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

}
