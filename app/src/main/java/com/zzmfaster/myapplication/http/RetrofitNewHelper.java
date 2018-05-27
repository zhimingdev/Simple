package com.zzmfaster.myapplication.http;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.zzmfaster.myapplication.http.api.RetrofitService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNewHelper {

    public static String Host ="http://www.yulin520.com/";


    private Context mContext;
    public static RetrofitNewHelper newinstance = null;


    //构建拦截器
    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(new HttpCommonInterceptor())
            .build();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    protected Retrofit retrofit;
    private RetrofitService retrofitService;

    public RetrofitNewHelper(Context mContext) {
        this.mContext = mContext;
        init();
    }

    public static RetrofitNewHelper getNewInstance(Context context) {
        if (newinstance == null) {
            newinstance = new RetrofitNewHelper(context);
        }
        return newinstance;
    }

    public void init() {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(RetrofitNewHelper.Host)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
