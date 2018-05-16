package com.zzmfaster.myapplication.http;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.zzmfaster.myapplication.http.api.RetrofitService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static String Host1 ="http://api.douban.com/";
    public static String Host2 ="http://120.27.23.105/";
    public static String DEFAULT = "DEFAULT";
    public static String DEFAULT_NEW = "DEFAULT_NEW";


    private Context mContext;
    private String type;
    public static RetrofitHelper instance = null;

    //构建拦截器
    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(new HttpCommonInterceptor())
            .build();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    protected Retrofit retrofit;
    private RetrofitService retrofitService;

    public RetrofitHelper(Context mContext,String type) {
        this.mContext = mContext;
        this.type = type;
        init(type);
    }


    public static RetrofitHelper getInstance(Context context,String type) {
        if (instance == null) {
            instance = new RetrofitHelper(context,type);
        }
        return instance;
    }

    public void init(String type) {
        if (RetrofitHelper.DEFAULT.equals(type)) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(RetrofitHelper.Host2)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }else {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(RetrofitHelper.Host1)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    public <T> ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
