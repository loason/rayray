package com.hh.android.stock.testkit.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hh.android.stock.testkit.R;
import com.hh.android.stock.testkit.base.BaseActivity;
import com.hh.android.stock.testkit.manager.TestKitManager;
import com.hh.android.stock.testkit.widget.CommonTitleView;

import java.util.Map;

/**
 * 设备信息
 * Created by litj on 2016/11/24.
 */

public class PageDeviceInfo extends BaseActivity {

    private CommonTitleView titleView;
    private TextView tvDeviceInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_info_layout);
        initView();
        initData();
    }

    private void initView() {
        titleView = (CommonTitleView) findViewById(R.id.title_view);
        tvDeviceInfo = (TextView) findViewById(R.id.tv_device_info);
        titleView.setOnLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        Map<String, String> deviceInfo = TestKitManager.getInstance().getDeviceInfo();
        StringBuilder sb = new StringBuilder();
        for (String key : deviceInfo.keySet()){
            sb.append(key + ":" + deviceInfo.get(key) + "\n");
        }
        tvDeviceInfo.setText(sb.toString());
    }
}
