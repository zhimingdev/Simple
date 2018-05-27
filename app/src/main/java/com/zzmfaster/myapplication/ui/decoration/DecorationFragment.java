package com.zzmfaster.myapplication.ui.decoration;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.bean.Msg;
import com.zzmfaster.myapplication.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DecorationFragment extends BaseFragment implements AMapLocationListener, View.OnClickListener {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.banner_rv)
    DiscreteScrollView bannerRv;
    @BindView(R.id.tv_text)
    TextView tvText;
    private BaseQuickAdapter adapter;
    private AMapLocationClient mLocationClient = null;////定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private String[] images = {"http://bpic.588ku.com/back_pic/02/51/47/12578201efb0f64.jpg",
            "http://bpic.588ku.com/back_pic/00/04/29/1356230ec0b1a60.jpg",
            "http://bpic.588ku.com/back_pic/00/02/75/41561a71adafd4a.jpg",
            "http://bpic.588ku.com/back_pic/03/64/71/8857ad8072cbb0a.jpg"};
    private List<String> list = new ArrayList<>();
    private Runnable autoRunnable;
    private PopupWindow popupWindow;
    private Animation scaleAnimations;

    public static DecorationFragment newInstance() {
        return new DecorationFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_decoration;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        for (int i = 0; i < images.length; i++) {
            list.add(images[i]);
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(mActivity);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        loadLocation();
        bannerRv.setOrientation(DSVOrientation.HORIZONTAL);
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_imageview_card) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                Glide.with(mActivity).load(item)
                        .bitmapTransform(new RoundedCornersTransformation(mActivity, 20, 0))
                        .into((ImageView) helper.getView(R.id.image));

                helper.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext,PhotoActivity.class);
//                        Intent intent = new Intent(mContext,TestPhotoActivity.class);
////                        intent.putExtra("url",item);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",item);
                        gotoActivity(TestPhotoActivity.class,false, bundle);
                    }
                });
            }
        };

        bannerRv.setItemTransitionTimeMillis(150);
        bannerRv.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.9f)
                .build());

        adapter.setNewData(list);
        autoRunnable = new Runnable() {
            @Override
            public void run() {
                if (bannerRv == null) {
                    return;
                }
                if (bannerRv.SCROLL_STATE_DRAGGING != bannerRv.getScrollState()) {
                    int currentItem = bannerRv.getCurrentItem();
                    bannerRv.smoothScrollToPosition(++currentItem);
                }
                bannerRv.postDelayed(this, 5000);
            }
        };
        bannerRv.setAdapter(InfiniteScrollAdapter.wrap(adapter));
        bannerRv.postDelayed(autoRunnable, 5000);

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPup();
            }
        });

    }

    @Override
    public void initData() {
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(MapActivity.class);
            }
        });
    }

    /**
     * 定位
     */
    private void loadLocation() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                String city = aMapLocation.getCity();
                String district = aMapLocation.getDistrict();//城区信息
                String street = aMapLocation.getStreet();//街道信息
                String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                tvLocation.setText(street);
            } else {
                tvLocation.setText("定位失败");
                int errorCode = aMapLocation.getErrorCode();
                String errorInfo = aMapLocation.getErrorInfo();
                System.out.println("错误码" + errorCode + "-----" + errorInfo);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bannerRv.removeCallbacks(autoRunnable);
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    public void showPup() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.popview_share, null);
        TextView delete = view.findViewById(R.id.iv_delete);
        TextView shareqq = view.findViewById(R.id.iv_share_qq);
        TextView shareqzone = view.findViewById(R.id.iv_share_qzone);
        TextView sharewechat = view.findViewById(R.id.iv_share_wechat);
        TextView sharemoment = view.findViewById(R.id.iv_share_wechat_moments);
        delete.setOnClickListener(this);
        shareqq.setOnClickListener(this);
        shareqzone.setOnClickListener(this);
        sharewechat.setOnClickListener(this);
        sharemoment.setOnClickListener(this);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        scaleAnimations = AnimationUtils.loadAnimation(getActivity(), R.anim.view_scaleanim);
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        view.startAnimation(scaleAnimations);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_delete:
                popupWindow.dismiss();
                break;
            case R.id.iv_share_qq:
                popupWindow.dismiss();
                OnekeyShare onekeyShare = new OnekeyShare();
                onekeyShare.setPlatform(QQ.NAME);
                onekeyShare.setTitle("想要迎娶白富美吗,想要资产过亿吗,那就洗洗睡吧");
                onekeyShare.setText("iphoneX今日降价,大甩卖");
                onekeyShare.setTitleUrl("https://www.maserati.com/maserati/cn/zh");
                onekeyShare.setImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=61339496,489409125&fm=200&gp=0.jpg");
                share(onekeyShare);
                popupWindow.dismiss();
                break;
            case R.id.iv_share_qzone:
                popupWindow.dismiss();
                OnekeyShare onekeyShare1 = new OnekeyShare();
                onekeyShare1.setPlatform(QZone.NAME);
                onekeyShare1.setTitle("想要迎娶白富美吗,想要资产过亿吗,那就洗洗睡吧");
                onekeyShare1.setText("iphoneX今日降价,大甩卖");
                onekeyShare1.setTitleUrl("https://www.maserati.com/maserati/cn/zh");
                onekeyShare1.setImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=61339496,489409125&fm=200&gp=0.jpg");
                share(onekeyShare1);
                break;
            case R.id.iv_share_wechat:
                popupWindow.dismiss();
                OnekeyShare share = new OnekeyShare();
                share.setPlatform(Wechat.NAME);
                share.setTitle("想要迎娶白富美吗,想要资产过亿吗,那就洗洗睡吧");
                share.setText("iphoneX今日降价,大甩卖");
                share.setUrl("https://www.maserati.com/maserati/cn/zh");
                share.setImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=61339496,489409125&fm=200&gp=0.jpg");
                share(share);
                break;
            case R.id.iv_share_wechat_moments:
                popupWindow.dismiss();
                OnekeyShare onekeyShare2 = new OnekeyShare();
                onekeyShare2.setPlatform(WechatMoments.NAME);
                onekeyShare2.setTitle("想要迎娶白富美吗,想要资产过亿吗,那就洗洗睡吧");
                onekeyShare2.setText("iphoneX今日降价,大甩卖");
                onekeyShare2.setUrl("https://www.maserati.com/maserati/cn/zh");
                onekeyShare2.setImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=61339496,489409125&fm=200&gp=0.jpg");
                share(onekeyShare2);
                break;
        }
    }

    //分享回调
    public void share(OnekeyShare share) {
        share.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                System.out.println("错误" + throwable.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                popupWindow.dismiss();
                ToastUtils.showToast(mActivity, "分享取消", 1000);
            }
        });
        share.show(mActivity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(Msg messageEvent) {
        String className = messageEvent.getMsg();
        switch (className) {
            case "1":
                tvText.setText("数据变化了");
                break;
        }
    }

}


