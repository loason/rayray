package com.hh.android.stock.testkit.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/11/24.
 */

public class BaseActivity extends FragmentActivity {

    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new WRHandler(this);
    }

    public void doHandleMessage(Message msg){}

    /**
     * 静态内部类，不会持有外部对象，不会造成内存泄露。另外再添加对外部对象的弱引用，可以访问外部方法
     */
    private static class WRHandler extends Handler {

        private WeakReference<BaseActivity> weakActivity;

        WRHandler(BaseActivity activity){
            weakActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            weakActivity.get().doHandleMessage(msg);
        }
    }

}
