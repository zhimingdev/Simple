package com.zzmfaster.myapplication.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzmfaster.myapplication.R;

import java.util.List;

public class PopuwindowUtils {

    private static PopupWindow popupWindow;
    private static String text;

    public static PopupWindow showPopup(Activity activity, TextView title,List arrs, int animation, View view) {
        return showPop(activity,title,arrs,animation,view);
    }

    private static PopupWindow showPop(Activity activity,TextView title,List arrs, int animation, View view) {
        View layout = LayoutInflater.from(activity).inflate(R.layout.popup_layout,null);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        RecyclerView recyclerView = layout.findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
//        // 给每个item添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(arrs);
        recyclerView.setAdapter(myAdapter);
        popupWindow.setContentView(layout);
        popupWindow.setAnimationStyle(animation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(view);
        layout.findViewById(R.id.ll_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                text = (String) arrs.get(position);
                title.setText(text);
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static class MyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.popup_item_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView content = helper.getView(R.id.listview_popwind_tv);
            content.setText(item);
        }

    }

}
