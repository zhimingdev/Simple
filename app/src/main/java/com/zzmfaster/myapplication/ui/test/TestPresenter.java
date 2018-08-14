package com.zzmfaster.myapplication.ui.test;

import com.zzmfaster.myapplication.MyApp;
import com.zzmfaster.myapplication.bean.GrilBean;
import com.zzmfaster.myapplication.framework.BasePresenter;
import com.zzmfaster.myapplication.http.BaseNewObserver;
import com.zzmfaster.myapplication.http.BaseRetData;
import com.zzmfaster.myapplication.http.RetrofitNewHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPresenter extends BasePresenter<TestContract.IView> implements TestContract.IPresenter {
    protected List<GrilBean> data;
    private List<GrilBean> list;

    @Override
    public void getDatas() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        RetrofitNewHelper.getNewInstance(MyApp.getInstance().getApplicationContext())
                .getRetrofitService()
                .getNews(map)
                .compose(setThread())
                .subscribe(new BaseNewObserver<List<GrilBean>>() {

                    @Override
                    protected void onSuccees(BaseRetData<List<GrilBean>> t) throws Exception {
                        data = t.getData();
                        view.refreshText(data);
                    }

                    @Override
                    protected void onCodeError(BaseRetData<List<GrilBean>> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e) throws Exception {

                    }
                });
    }
}
