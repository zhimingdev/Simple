package com.zzmfaster.myapplication.ui.find;

import android.app.AlertDialog;
import android.view.View;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.utils.DialogUtils;

public class FindFragment extends BaseFragment {

    private AlertDialog dialog;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
//        getResources().getDrawable(R.drawable.shape_dialogtext_rightbg)
        dialog = DialogUtils.show(mActivity, "温馨提示", "这是测试问饿呢", "#272727", "#46A3FF",
                getResources().getDrawable(R.drawable.shape_dialogtext_rightbg),true, new String[]{"不好","好"}, null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
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
            dialog.show();
        }
    }
}
