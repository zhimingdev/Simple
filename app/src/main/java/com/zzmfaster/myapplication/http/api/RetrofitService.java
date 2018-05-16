package com.zzmfaster.myapplication.http.api;

import com.zzmfaster.myapplication.bean.ComputerBean;
import com.zzmfaster.myapplication.bean.MovieBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @GET("v2/movie/top250")
    Observable<MovieBean> getSearchBooks(@QueryMap Map<String,String> map);

    @GET("product/searchProducts")
    Observable<List<ComputerBean>> getProducts(@QueryMap Map<String,String> map);

}
