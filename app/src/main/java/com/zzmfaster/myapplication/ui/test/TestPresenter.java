package com.zzmfaster.myapplication.ui.test;

import com.zzmfaster.myapplication.framework.BasePresenter;

public class TestPresenter extends BasePresenter<TestContract.IView> implements TestContract.IPresenter {

    public void refreshText() {

        view.refreshText();
    }

}
