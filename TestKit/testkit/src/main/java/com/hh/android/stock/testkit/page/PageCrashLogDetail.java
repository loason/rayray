package com.hh.android.stock.testkit.page;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hh.android.stock.testkit.R;
import com.hh.android.stock.testkit.base.BaseActivity;
import com.hh.android.stock.testkit.widget.CommonTitleView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 奔溃信息详情页
 * Created by litj on 2016/11/22.
 */

public class PageCrashLogDetail extends BaseActivity {

    public static final String FILE_PATH = "file_path";

    private CommonTitleView titleView;
    private TextView tvCrashDetailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_crash_log_detail_layout);
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if(!TextUtils.isEmpty(bundle.getString(FILE_PATH))){
            File file = new File(bundle.getString(FILE_PATH));
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String line = "";
                while((line = br.readLine())!=null){
                    sb.append(line);
                }
                br.close();
                tvCrashDetailInfo.setText(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void initView() {
        titleView = (CommonTitleView) findViewById(R.id.title_view);
        titleView.setOnLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvCrashDetailInfo = (TextView) findViewById(R.id.tv_crash_detail_info);
    }
}
