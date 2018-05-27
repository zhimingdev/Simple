package com.zzmfaster.myapplication.ui.mine;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.adapter.MsgAdapter;
import com.zzmfaster.myapplication.base.BaseActivity;
import com.zzmfaster.myapplication.bean.GrilBean;
import com.zzmfaster.myapplication.http.BaseNewObserver;
import com.zzmfaster.myapplication.http.BaseRetData;
import com.zzmfaster.myapplication.http.RetrofitNewHelper;
import com.zzmfaster.myapplication.ui.decoration.TestPhotoActivity;
import com.zzmfaster.myapplication.utils.CalendarReminderUtils;
import com.zzmfaster.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;

public class MsgActivity extends BaseActivity {

    @BindView(R.id.msg_rcv)
    RecyclerView msgRcv;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private List<GrilBean> list = new ArrayList<>();
    private MsgAdapter msgAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        msgRcv.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter();
        msgRcv.setAdapter(msgAdapter);
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        RetrofitNewHelper.getNewInstance(mContext)
                .getRetrofitService()
                .getNews(map)
                .compose(this.setThread())
                .subscribe(new BaseNewObserver<List<GrilBean>>() {

                    @Override
                    protected void onSuccees(BaseRetData<List<GrilBean>> t) throws Exception {
                        list.clear();
                        list.addAll(t.getData());
                        msgAdapter.setNewData(list);
                    }

                    @Override
                    protected void onCodeError(BaseRetData<List<GrilBean>> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e) throws Exception {

                    }
                });
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String img = list.get(position).getImg();
                Bundle bundle = new Bundle();
                bundle.putString("url", img);
                startActivity(TestPhotoActivity.class, bundle);
            }
        });

        msgAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //先定义一个URL，到时作为调用系统日历的uri的参数
                String calanderRemiderURL = "";
                String calanderURL = "content://com.android.calendar/calendars";
                if (Build.VERSION.SDK_INT >= 8) {
                    calanderRemiderURL = "content://com.android.calendar/reminders";
                } else {
                    calanderRemiderURL = "content://calendar/reminders";
                }
                String calID = "";
                long startMillis = 0;
                long endMillis = 0;

                Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
                if (userCursor.getCount() > 0) {
                    userCursor.moveToLast();  //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
                    calID = userCursor.getString(userCursor.getColumnIndex("_id"));
                }
                else {
                    ToastUtils.showToast(MsgActivity.this, "没有账户，请先添加账户", 1500);
                }
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2018, 4, 23, 7, 30);  //注意，月份的下标是从0开始的
                startMillis = beginTime.getTimeInMillis();  //插入日历时要取毫秒计时
                Calendar endTime = Calendar.getInstance();
                endTime.set(2018, 4, 23, 10, 30);
                endMillis = endTime.getTimeInMillis();

                ContentValues eValues = new ContentValues();  //插入事件
                ContentValues rValues = new ContentValues();  //插入提醒，与事件配合起来才有效
                TimeZone tz = TimeZone.getDefault();//获取默认时区

//插入日程
                eValues.put(CalendarContract.Events.DTSTART, startMillis);
                eValues.put(CalendarContract.Events.DTEND, endMillis);
                eValues.put(CalendarContract.Events.TITLE, "见导师");
                eValues.put(CalendarContract.Events.DESCRIPTION, "去实验室见研究生导师");
                eValues.put(CalendarContract.Events.CALENDAR_ID, calID);
                eValues.put(CalendarContract.Events.EVENT_LOCATION, "计算机学院");
                eValues.put(CalendarContract.Events.EVENT_TIMEZONE, tz.getID());
                Uri uri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, eValues);

//插完日程之后必须再插入以下代码段才能实现提醒功能
                String myEventsId = uri.getLastPathSegment(); // 得到当前表的_id
                rValues.put("event_id", myEventsId);
                rValues.put("minutes", 10); //提前10分钟提醒
                rValues.put("method", 1);   //如果需要有提醒,必须要有这一行
                getContentResolver().insert(Uri.parse(calanderRemiderURL),rValues);

                CalendarReminderUtils.addCalendarEvent(mContext,"约会去",list.get(position).getTitle(),System.currentTimeMillis(),1);
                return true;
            }
        });
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
