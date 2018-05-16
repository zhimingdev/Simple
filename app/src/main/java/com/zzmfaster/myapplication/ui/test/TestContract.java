package com.zzmfaster.myapplication.ui.test;

import com.zzmfaster.myapplication.framework.IBaseView;

public interface TestContract {

    interface IView extends IBaseView {
        //界面刷新的操作防止于此
        void refreshText();
    }

    interface IPresenter{
        //网络请求,数据处理,逻辑处理等操作
    }
}
