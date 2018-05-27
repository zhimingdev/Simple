package com.zzmfaster.myapplication.framework;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter <V extends IBaseView> implements IBasePresenter {
    protected V view;

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }

    public void setView(V view) {
        this.view = view;
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
