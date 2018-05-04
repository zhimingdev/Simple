package com.zzmfaster.myapplication.ui.test;

import com.zzmfaster.myapplication.framework.IBaseView;

public interface TestContract {

    interface IView extends IBaseView {
        void refreshText();
    }

    interface IPresenter{

    }
}
