package com.zzmfaster.myapplication.framework;

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

}
