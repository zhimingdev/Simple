package com.zzmfaster.myapplication.ui.find.grils;

import com.zzmfaster.myapplication.bean.ResponseGrilsBean;
import com.zzmfaster.myapplication.framework.IBasePresenter;
import com.zzmfaster.myapplication.framework.IBaseView;

import java.util.List;

public interface GirlsContact {
    interface IVIEW extends IBaseView {
        void refreshGirls(List<ResponseGrilsBean.ResultsBean> resultsBean);
    }

    interface Ipresenter extends IBasePresenter {
        void getGirlsData();
    }
}
