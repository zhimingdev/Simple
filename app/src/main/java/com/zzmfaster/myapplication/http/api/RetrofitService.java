package com.zzmfaster.myapplication.http.api;

import com.zzmfaster.myapplication.bean.GrilBean;
import com.zzmfaster.myapplication.bean.MovieBean;
import com.zzmfaster.myapplication.http.BaseRetData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @GET("v2/movie/top250")
    Observable<MovieBean> getSearchBooks(@QueryMap Map<String,String> map);

    @GET("a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&")
    Observable<BaseRetData<List<GrilBean>>> getNews(@QueryMap Map<String,String> map);

}
