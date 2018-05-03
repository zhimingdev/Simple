package com.zzmfaster.myapplication.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.zzmfaster.myapplication.R;

public class DialogUtils {

    public static AlertDialog show(Activity activity, String title, Object content, String leftcolor,
                                   String rightColor, Drawable bgcolor, boolean isshow, String[] arrs, View.OnClickListener clostlis, View.OnClickListener poslis) {
        return showDialog(activity,title,content,leftcolor,rightColor,bgcolor,isshow,arrs,clostlis,poslis);
    }

    private static AlertDialog showDialog(Activity activity, String title, Object content, String leftcolor,
                                          String rightColor, Drawable bgcolor, boolean isshow,
                                          String[] arrs, View.OnClickListener clostlis, View.OnClickListener poslis) {
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.dialog).create();
        Window window = activity.getWindow();
        window.setWindowAnimations(R.style.popwin_anim_style);
        dialog.setCanceledOnTouchOutside(true);

        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_layout, null);
        TextView titl = inflate.findViewById(R.id.tv_title);
        TextView text = inflate.findViewById(R.id.tv_content);

        ImageView close = inflate.findViewById(R.id.iv_close);
        ImageView top = inflate.findViewById(R.id.iv_top);
        ImageView middle = inflate.findViewById(R.id.iv_middle);
        ImageView end = inflate.findViewById(R.id.iv_end);

        TextView cancel = inflate.findViewById(R.id.tv_noposition);
        TextView sure = inflate.findViewById(R.id.tv_position);

        if (ObjectUtils.isEmpty(title)) {
            titl.setVisibility(View.INVISIBLE);
            top.setVisibility(View.INVISIBLE);
        }else {
            titl.setText(title);
        }

        if (content instanceof SpannableStringBuilder) {
            text.setText(((SpannableStringBuilder) content));
        }else {
            text.setText(content.toString());
        }

        if (!ObjectUtils.isEmpty(leftcolor)) {
            cancel.setTextColor(Color.parseColor(leftcolor));
        }

        if (!ObjectUtils.isEmpty(rightColor)) {
            sure.setTextColor(Color.parseColor(rightColor));
        }

        if (bgcolor != null) {
            sure.setBackground(bgcolor);
        }


        if (!isshow) {
            close.setVisibility(View.GONE);
        }else {
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (arrs.length <= 1) {
            cancel.setVisibility(View.GONE);
            end.setVisibility(View.GONE);
            sure.setText(arrs[0]);
            sure.setOnClickListener(poslis);
        }else {
            cancel.setText(arrs[0]);
            sure.setText(arrs[1]);
            cancel.setOnClickListener(clostlis);
            sure.setOnClickListener(poslis);
        }
        dialog.setView(inflate);
        dialog.show();
        return dialog;
    }
}
