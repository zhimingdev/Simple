package com.zzmfaster.myapplication.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjh89 on 2018/1/25.
 */

public class RxUtil {

    public static final long INTERVAL = 60;

    /**
     * 实现IO线程与主线程之间的切换
     */
    public static void switchThread(final OnEventListener ioListener, final OnEventListener mainListener) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Object o = ioListener.onEvent(null);

                if (ObjectUtils.isEmpty(o)) {
                    o = new Object();
                }

                emitter.onNext(o);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mainListener.onEvent(o);
                    }
                });
    }

    public static void singleClick(View view) {
        singleClick(view, null);
    }

    /**
     * 1秒内防重点击
     *
     * @param view
     * @param listener
     */
    public static void singleClick(View view, final OnEventListener listener) {
        RxView.clicks(view)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (listener != null) {
                            try {
                                listener.onEvent(null);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.i("RxUtil", "requestCode onEvent error:" + e.getMessage());
                            }
                        }
                    }
                });
    }

    public static void requestCode(TextView textView) {
        requestCode(textView, INTERVAL, null, null);
    }


    public static void requestCode(TextView textView, OnDataListener onDataListener, OnEventListener listener) {
        requestCode(textView, INTERVAL, onDataListener, listener);
    }

    /**
     * 请求验证码
     *
     * @param textView
     * @param interval
     * @param listener
     */
    @SuppressLint("CheckResult")
    public static void requestCode(final TextView textView, final long interval, final OnDataListener onDataListener, final OnEventListener listener) {
        RxView.clicks(textView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (!ObjectUtils.isEmpty(onDataListener)) {
                            if (!onDataListener.checkData()) {
                                return;
                            }
                        }

                        Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.io())
                                .take(interval, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Long>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        textView.setEnabled(false);
                                        textView.setTextColor(Color.parseColor("#FFCCCCCC"));
                                        if (listener != null) {
                                            try {
                                                listener.onEvent(null);
                                                ToastUtils.showLong("验证码发送中");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.i("RxUtil", "requestCode onEvent error:" + e.getMessage());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNext(Long time) {
                                        textView.setText(interval - time + "秒后重新获取");
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        textView.setEnabled(true);
                                        textView.setTextColor(Color.parseColor("#FF4699FA"));
                                        textView.setText("重新获取");
                                    }
                                });
                    }
                });
    }


    public static void textChange(TextView textView, final OnEventListener listener) {
        textChange(textView, 1000, listener);
    }

    /**
     * 内容改变（过滤小与timeOut间隔的改变）
     *
     * @param textView
     * @param timeOut
     * @param listener
     */
    @SuppressLint("CheckResult")
    public static void textChange(TextView textView, long timeOut, final OnEventListener listener) {
        RxTextView.afterTextChangeEvents(textView)
                .subscribeOn(Schedulers.io())
                .debounce(timeOut, TimeUnit.MILLISECONDS)
                .filter(new Predicate<TextViewAfterTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewAfterTextChangeEvent event) throws Exception {
                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(TextViewAfterTextChangeEvent event) throws Exception {
                        if (listener != null) {
                            try {
                                listener.onEvent(event.editable().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.i("RxUtil", "requestCode onEvent error:" + e.getMessage());
                            }
                        }
                    }
                });
    }

    public interface OnDataListener {
        boolean checkData();
    }

    public interface OnEventListener {
        Object onEvent(Object event);
    }
}
