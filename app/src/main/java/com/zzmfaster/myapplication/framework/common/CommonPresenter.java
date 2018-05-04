package com.zzmfaster.myapplication.framework.common;

import com.zzmfaster.myapplication.framework.BasePresenter;


/**
 * 公共方法契约类
 */

public class CommonPresenter extends BasePresenter<ICommonContract.IView> implements ICommonContract.IBasePresenter {

    @Override
    public void requestData() {
        view.refreshData();
    }
}
