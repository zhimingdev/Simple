package com.zzmfaster.myapplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

public class MyApp extends MultiDexApplication {
    private static Application instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        SophixManager.getInstance().setContext(this)
                .setAppVersion("1.0")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        MobSDK.init(this);
        MultiDex.install(this);
        Utils.init(this);
//        MyObjectBox.builder().androidContext(this).build();
// queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        UMConfigure.setLogEnabled(true);
        UMShareAPI.get(this);
        SophixManager.getInstance().queryAndLoadNewPatch();
        UMConfigure.init(this, "5b2b3e91b27b0a02fd00006c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        PlatformConfig.setWeixin("wxf181d3d8e0e201a0", "634067b6db506698845f1340cdd850b3");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Application getInstance(){
        return instance;
    }
}
