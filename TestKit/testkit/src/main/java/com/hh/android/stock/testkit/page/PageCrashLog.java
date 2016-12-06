package com.hh.android.stock.testkit.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hh.android.stock.testkit.R;
import com.hh.android.stock.testkit.base.BaseActivity;
import com.hh.android.stock.testkit.widget.CommonTitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看奔溃日志
 * Created by litj on 2016/11/22.
 */

public class PageCrashLog extends BaseActivity {

    private CommonTitleView titleView;
    private ListView lvCrashLog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.crash_log_layout);
        lvCrashLog = (ListView) findViewById(R.id.lv_crash_log);
        initView();
        getCrashLogList();
    }

    private void getCrashLogList() {
        File file = new File(getCrashLogDir());
        if(file.isDirectory()){
            List<String> path = new ArrayList<>();
            List<String> fileName = new ArrayList<>();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++){
                path.add(files[i].getAbsolutePath());
                fileName.add(files[i].getName());
            }
            CrashLogAdapter adapter = new CrashLogAdapter(path, fileName);
            lvCrashLog.setAdapter(adapter);
        }
    }

    /**
     * 得到奔溃日志目录
     * @return 返回奔溃日志目录
     */
    public String getCrashLogDir() {
        try {
            String dirPath = Environment.getExternalStorageDirectory() + File.separator + "TestKit" + File.separator + "CrashLog";
            File tmpDir = new File(dirPath);
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }
            return tmpDir.getAbsolutePath();
        } catch (Exception e) {
            return null;
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
    }

    private class CrashLogAdapter extends BaseAdapter{

        List<String> crashLogPath;
        List<String> crashLogFileName;

        public CrashLogAdapter(List<String> crashLogPath, List<String> crashLogFileName){
            this.crashLogPath = crashLogPath;
            this.crashLogFileName = crashLogFileName;
        }

        @Override
        public int getCount() {
            return crashLogFileName == null ? 0 : crashLogFileName.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            FileNameViewHolder vh = null;
            if(convertView == null){
                vh = new FileNameViewHolder();
                convertView = LayoutInflater.from(PageCrashLog.this).inflate(R.layout.crash_log_item, null);
                vh.tvFileName = (TextView) convertView.findViewById(R.id.tv_file_name);
                convertView.setTag(vh);
            }
            else{
                vh = (FileNameViewHolder) convertView.getTag();
            }
            vh.tvFileName.setText(crashLogFileName.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PageCrashLog.this, PageCrashLogDetail.class);
                    intent.putExtra(PageCrashLogDetail.FILE_PATH, crashLogPath.get(position));
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class FileNameViewHolder{
            TextView tvFileName;
        }

    }

}
