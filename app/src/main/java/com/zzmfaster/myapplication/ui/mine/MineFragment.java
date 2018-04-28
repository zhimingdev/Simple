package com.zzmfaster.myapplication.ui.mine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;

import butterknife.BindView;

public class MineFragment extends BaseFragment {

    @BindView(R.id.root)
    LinearLayout root;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        final DrawView view = new DrawView(mActivity);
        view.setMinimumHeight(500);
        view.setMinimumWidth(300);
        //通知view组件重绘
        view.invalidate();
        root.addView(view);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    public class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            /***********配置画笔*************/
             Paint paint=new Paint();    //采用默认设置创建一个画笔
             paint.setAntiAlias(true);//使用抗锯齿功能
             paint.setColor(0xFFff0000);    //设置画笔的颜色为绿色
             paint.setStyle(Paint.Style.STROKE);//设置画笔类型为STROKE类型（个人感觉是描边的意思）
             /***********绘制圆弧*************/
             RectF rectf_head=new RectF(20, 20, 200, 200);//确定外切矩形范围
             rectf_head.offset(100, 20);//使rectf_head所确定的矩形向右偏移100像素，向下偏移20像素
             canvas.drawArc(rectf_head, -10, -160, false, paint);//绘制圆弧，不含圆心
        }
    }

}
