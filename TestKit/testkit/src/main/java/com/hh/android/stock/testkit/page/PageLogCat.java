package com.hh.android.stock.testkit.page;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.android.stock.testkit.R;
import com.hh.android.stock.testkit.base.BaseActivity;
import com.hh.android.stock.testkit.widget.CommonTitleView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 查看logcat
 * Created by litj on 2016/11/22.
 */

public class PageLogCat extends BaseActivity {

    /* 是否停止log打印 */
    private boolean isStopLog = false;

    private CommonTitleView titleView;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.log_cat_layout);
        initView();
    }

    private void initView() {
        titleView = (CommonTitleView) findViewById(R.id.title_view);
        titleView.setOnLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvLog = (TextView) findViewById(R.id.tv_log);
        tvLog.setText("暂无");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Process mLogcatProc = null;
//                BufferedReader reader = null;
//                try {
//                    //获取logcat日志信息
//                    mLogcatProc = Runtime.getRuntime().exec(new String[] { "logcat","request:I *:S" });
//                    reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
//                    String line;
//                    while (!isStopLog) {
//                        if((line = reader.readLine()) != null) {
//                            //mHandler.sendMessage(mHandler.obtainMessage(0, line));
//                            final String temp = line;
//                            PageLogCat.this.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    tvLog.setText(tvLog.getText() + temp);
//                                }
//                            });
//                        }
//                        Log.d("PageLogCata", "while");
//                        Thread.sleep(300);
//                    }
//                } catch (Exception e) {
//                    Log.d("PageLogCata", "Exception");
//                    e.printStackTrace();
//                }
//                Log.d("PageLogCata", "stop");
//            }
//        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //isStopLog = true;
    }

    @Override
    public void doHandleMessage(Message msg) {
        //tvLog.setText(tvLog.getText() + (msg.obj + ""));
    }
}
