package com.zzmfaster.myapplication.ui.decoration

import android.os.Bundle
import com.zzmfaster.myapplication.R
import com.zzmfaster.myapplication.base.BaseActivity

class PhotoActivity:BaseActivity() {
    override fun initData() {
    }

    override fun initView(savedInstanceState: Bundle?) {
//        var stringExtra = intent.getStringExtra("url")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_photoview
    }

}