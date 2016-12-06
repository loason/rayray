package com.hh.android.stock.testkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hh.android.stock.testkit.manager.TestKitManager;
import com.hh.android.stock.testkit.page.PageChangeAddress;
import com.hh.android.stock.testkit.page.PageCrashLog;
import com.hh.android.stock.testkit.page.PageDeviceInfo;
import com.hh.android.stock.testkit.page.PageLogCat;

import java.util.Map;


/**
 * 测试工具首页
 * Created by litj on 2016/11/21.
 */

public class TestMainActivity extends Activity {

    private TextView tvInfo;
    private Button btnChangeAddress;
    private Button btnCrashLog;
    private Button btnLogCat;
    private Button btnDeviceInfo;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.test_main_layout);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Map<String, String> info = TestKitManager.getInstance().getVersionInfo(this);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        sb.append("当前请求地址：" + TestKitManager.getInstance().getCurrentAddress());
        tvInfo.setText(sb.toString());
    }

    private void initViews() {
        tvInfo = (TextView) findViewById(R.id.tv_info);
        btnChangeAddress = (Button) findViewById(R.id.btn_change_address);
        btnCrashLog = (Button) findViewById(R.id.btn_crash_log);
        btnLogCat = (Button) findViewById(R.id.btn_log_cat);
        btnDeviceInfo = (Button) findViewById(R.id.btn_device_info);
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, PageChangeAddress.class);
                startActivity(intent);
            }
        });
        btnCrashLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, PageCrashLog.class);
                startActivity(intent);
            }
        });
        btnLogCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, PageLogCat.class);
                startActivity(intent);
            }
        });
        btnDeviceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, PageDeviceInfo.class);
                startActivity(intent);
            }
        });
    }
}
