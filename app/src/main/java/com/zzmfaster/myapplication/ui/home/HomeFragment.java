package com.zzmfaster.myapplication.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zzmfaster.myapplication.Constant;
import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.adapter.MovieAdapter;
import com.zzmfaster.myapplication.base.BaseFragment;
import com.zzmfaster.myapplication.bean.MovieBean;
import com.zzmfaster.myapplication.custom.zxing.activity.CaptureActivity;
import com.zzmfaster.myapplication.http.BaseObserver;
import com.zzmfaster.myapplication.http.BaseRetData;
import com.zzmfaster.myapplication.http.RetrofitHelper;
import com.zzmfaster.myapplication.ui.CommonalityActivity;
import com.zzmfaster.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.support.v7.app.AppCompatActivity.RESULT_OK;

public class HomeFragment extends BaseFragment {

    //    @BindView(R.id.sv)
//    MyScrollview sv;
    @BindView(R.id.nsv)
    NestedScrollView nestedScrollView;
    @BindView(R.id.searchBarLayout)
    ImageView searchBarLayout;
    @BindView(R.id.tv_ll)
    LinearLayout tvLl;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.srfl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.filpper)
    ViewFlipper filPer;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.rlv)
    RecyclerView rlv;

    private int imageHeight;
    private String[] images = {"http://bpic.588ku.com/back_pic/00/03/40/63561e40448baf8.jpg",
            "http://bpic.588ku.com/back_pic/00/02/92/61561bd20f6c1ed.jpg",
            "http://bpic.588ku.com/back_pic/00/02/93/93561be64e6add2.jpg",
            "http://bpic.588ku.com/back_pic/00/01/55/805604f987b152c.jpg"};
    List<String> list = new ArrayList<>();
    List<String> list1 = new ArrayList<>();

    private String[] texts = {"测试文本1", "测试文本2", "测试文本3", "测试文本4", "测试文本5"};
    List<MovieBean.SubjectsBean> lists = new ArrayList<>();
    private List<MovieBean.SubjectsBean> subjects;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        for (String item : images) {
            list.add(item);
        }
        ViewTreeObserver vto = tvLl.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvLl.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = tvLl.getHeight();
                nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollY <= 0) {
                            tvLl.setAlpha(0.0f);
                            ivCode.setEnabled(false);
                        } else if (scrollY > 0 && scrollY <= imageHeight) {
                            float scale = (float) scrollY / imageHeight;
                            // 只是layout背景透明
                            tvLl.setAlpha(scale);
                            ivCode.setEnabled(false);
                        } else {
                            ivCode.setEnabled(true);
                            tvLl.setAlpha(1f);
                        }
                    }
                });
            }
        });
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(list);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        banner.start();
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });
        startFilper();
    }

    private void startFilper() {
        for (String string : texts) {
            list1.add(string);
        }
        for (String s : list1) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.filper_item, null);
            TextView tv = view.findViewById(R.id.tv);
            tv.setText(s);
            filPer.addView(view);
        }
        filPer.setFlipInterval(3000);
        filPer.startFlipping();
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rlv.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setHasFixedSize(true);
        rlv.setNestedScrollingEnabled(false);
        MovieAdapter movieAdapter = new MovieAdapter();
        rlv.setAdapter(movieAdapter);
        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQrCode();
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        RetrofitHelper.getInstance(mActivity,Constant.DEFAULT)
                .getRetrofitService()
                .getSearchBooks(map)
                .compose(this.setThread())
                .subscribe(new BaseObserver<MovieBean>() {

                    @Override
                    protected void onSuccees(MovieBean t) throws Exception {
                        subjects = t.getSubjects();
                        movieAdapter.setNewData(subjects);
                    }

                    @Override
                    protected void onCodeError(BaseRetData<MovieBean> t) throws Exception {

                    }


                    @Override
                    protected void onFailure(Throwable e) throws Exception {
                    }
                });

        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MovieBean.SubjectsBean subjectsBean = subjects.get(position);
                String alt = subjectsBean.getAlt();
                Bundle bundle = new Bundle();
                bundle.putString("result",alt);
                gotoActivity(CommonalityActivity.class,false,bundle);
            }
        });

    }

    @Override
    protected void initImmersionBar(boolean isChange) {
        super.initImmersionBar(true);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);


            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            ImageView imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(mActivity, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);

//            跳转到自己的webview打开
            Bundle bundle1 = new Bundle();
            bundle1.putString("result", scanResult);
            gotoActivity(CommonalityActivity.class, false, bundle1);

            //直接用默认浏览器打开
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse(scanResult);
//            intent.setData(content_url);
//            startActivity(intent);
        } else {
            ToastUtils.showToast(mActivity, "解析二维码失败", 1500);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    ToastUtils.showToast(mActivity, "请至权限中心打开本应用的相机访问权限", 1500);
                }
                break;
        }
    }
}
