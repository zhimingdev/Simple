package com.zzmfaster.myapplication.framework;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzmfaster.myapplication.R;
import com.zzmfaster.myapplication.bean.Msg;
import com.zzmfaster.myapplication.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseMvpActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    private AlertDialog dialog;
    protected List<BasePresenter> presenterList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        configPresenter();
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void creatPresenter(Class<? extends BasePresenter>... classes) {
        if (presenterList == null) {
            presenterList = new ArrayList<>();
        }

        for (Class aclass:classes) {
            try {
                BasePresenter presenter = (BasePresenter) aclass.newInstance();
                presenter.setView(this);
                presenterList.add(presenter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <P extends BasePresenter> P getPresenter(Class<P> aClass) {
        for (BasePresenter basePresenter:presenterList) {
            if (basePresenter.getClass() == aClass) {
                return (P)basePresenter;
            }
        }
        return null;
    }

    @Override
    public void showLoadingDialog() {
        dialog = DialogUtils.show(this, "温馨提示", "这是测试问饿呢", "#272727", "#46A3FF",
                getResources().getDrawable(R.drawable.shape_dialogtext_rightbg), true, new String[]{"不好", "好"}, null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().postSticky(new Msg("1"));
                        dialog.dismiss();
                    }
                });
    }


    public abstract int getlayoutId();
    public abstract void initView();

}