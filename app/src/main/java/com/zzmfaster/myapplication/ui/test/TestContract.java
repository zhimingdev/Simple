package com.zzmfaster.myapplication.ui.test;

import com.zzmfaster.myapplication.bean.GrilBean;
import com.zzmfaster.myapplication.framework.IBaseView;

import java.util.List;

public interface TestContract {

    interface IView extends IBaseView {
        //界面刷新的操作防止于此
        void refreshText(List<GrilBean> data);
    }

    interface IPresenter{
        //网络请求,数据处理,逻辑处理等操作
        void getDatas();

    }
}
