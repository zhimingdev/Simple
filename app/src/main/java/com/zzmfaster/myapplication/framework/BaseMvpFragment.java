package com.zzmfaster.myapplication.framework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseMvpFragment extends Fragment implements IBaseView {
    private View mContentView;
    private Unbinder unbinder;
    protected List<BasePresenter> presenterList;
    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        configPresenter();
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        initView();
        initData();
        return mContentView;
    }

    public void creatPresenter(Class<? extends BasePresenter>... classes) {
        if (presenterList == null) {
            presenterList = new ArrayList<>();
        }

        for (Class aclass:classes) {
            try {
                BasePresenter presenter = (BasePresenter) aclass.newInstance();
                presenter.setView(this);
                presenterList.add(presenter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <P extends BasePresenter> P getPresenter(Class<P> aClass) {
        for (BasePresenter basePresenter:presenterList) {
            if (basePresenter.getClass() == aClass) {
                return (P)basePresenter;
            }
        }
        return null;
    }


    public <T> ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public abstract int setLayoutResourceID();

    public abstract void initView();
    public abstract void initData();
}
