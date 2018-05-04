package com.zzmfaster.myapplication.framework.common;

import com.zzmfaster.myapplication.framework.IBaseView;

public interface ICommonContract {

    interface IView extends IBaseView{
        void refreshData();
    }

    interface IBasePresenter {
        void requestData();
    }
}
