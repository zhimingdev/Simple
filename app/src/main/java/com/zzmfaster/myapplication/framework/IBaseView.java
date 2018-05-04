package com.zzmfaster.myapplication.framework;

public interface IBaseView {
    void showLoadingDialog();

    void configPresenter();

    void creatPresenter(Class<? extends BasePresenter>... classes);

    <P extends BasePresenter> P getPresenter(Class<P> aClass);

}
