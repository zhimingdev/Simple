package com.zzmfaster.myapplication.ui.find.grils;

import com.zzmfaster.myapplication.Constant;
import com.zzmfaster.myapplication.MyApp;
import com.zzmfaster.myapplication.bean.ResponseGrilsBean;
import com.zzmfaster.myapplication.framework.BasePresenter;
import com.zzmfaster.myapplication.http.BaseObserver;
import com.zzmfaster.myapplication.http.BaseRetData;
import com.zzmfaster.myapplication.http.RetrofitHelper;

import java.util.List;

public class GirlsPresenter extends BasePresenter<GirlsContact.IVIEW> implements GirlsContact.Ipresenter {
    @Override
    public void getGirlsData() {
        RetrofitHelper.getInstance(MyApp.getInstance().getApplicationContext(), Constant.CHOOSE)
                .getRetrofitService()
                .getGirls()
                .compose(setThread())
                .subscribe(new BaseObserver<ResponseGrilsBean>() {
                    @Override
                    protected void onSuccees(ResponseGrilsBean responseGrilsBean) throws Exception {
                        List<ResponseGrilsBean.ResultsBean> results = responseGrilsBean.getResults();
                        view.refreshGirls(results);
                    }

                    @Override
                    protected void onCodeError(BaseRetData<ResponseGrilsBean> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e) throws Exception {

                    }
                });
    }

}
