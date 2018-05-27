package com.zzmfaster.myapplication.ui.find;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.utils.AnimaUtils;
import com.zzmfaster.myapplication.utils.PopuwindowUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class FindFragment extends BaseFragment {

    @BindView(R.id.iv_one)
    ImageView ivOne;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.find_fl)
    FrameLayout findFl;
    private AlertDialog dialog;
    String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品",
            "酒水饮料", "水果"};
    String[] menuStr2 = new String[]{"不限", "综合排序", "配送费最低"};
    String[] menuStr3 = new String[]{"不限", "优惠活动", "特价活动", "免配送费",
            "可在线支付"};


    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        List<String> list = (List<String>) Arrays.asList(menuStr1);
        List<String> list1 = Arrays.asList(menuStr2);
        List<String> list2 = Arrays.asList(menuStr3);
        tvName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.openAnima(ivOne);
                PopupWindow popupWindow = (PopupWindow) PopuwindowUtils.showPopup(mActivity, tvName1, list, R.anim.view_scaleanim, llTop);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        AnimaUtils.closeAnima(ivOne);
                    }
                });
            }
        });

        tvName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.openAnima(ivTwo);
                PopupWindow popupWindow = (PopupWindow) PopuwindowUtils.showPopup(mActivity, tvName2, list1, R.anim.view_scaleanim, llTop);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        AnimaUtils.closeAnima(ivTwo);
                    }
                });
            }
        });

        tvName3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.openAnima(ivThree);
                PopupWindow popupWindow = (PopupWindow) PopuwindowUtils.showPopup(mActivity, tvName3, list2, R.anim.view_scaleanim, llTop);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss()  {
                        AnimaUtils.closeAnima(ivThree);
                    }
                });
            }
        });


    }

    @Override
    public void initData() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            dialog.show();
        }
    }

}
