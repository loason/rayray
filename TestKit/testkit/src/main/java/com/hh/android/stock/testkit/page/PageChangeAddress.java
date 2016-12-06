package com.hh.android.stock.testkit.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.android.stock.testkit.R;
import com.hh.android.stock.testkit.base.BaseActivity;
import com.hh.android.stock.testkit.manager.TestKitManager;
import com.hh.android.stock.testkit.util.SpUtil;
import com.hh.android.stock.testkit.widget.CommonTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 修改地址页
 * Created by litj on 2016/11/21.
 */

public class PageChangeAddress extends BaseActivity {

    private static final String KEY_ADDRESS = "key_address";

    private List<String> historyList = new ArrayList<>();
    private String historyStr;

    private CommonTitleView titleView;
    private ListView lvHistory;
    private EditText etAddress;
    private Button btnSet;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.change_address_layout);
        initView();
        initData();
    }

    private void initData() {
        /* 添加备选地址 */
        historyList.clear();
        historyList.add("http://teststock.winjijin.com");
        historyStr = SpUtil.getStringValue(this, KEY_ADDRESS);
        if(!TextUtils.isEmpty(historyStr)){
            String[] temp = historyStr.split(",");
            if(temp.length > 0){
                for(int i = 0; i < temp.length; i++){
                    historyList.add(temp[i]);
                }
            }
        }
        lvHistory.setAdapter(new AddressAdapter());
        etAddress.setText(TestKitManager.getInstance().getCurrentAddress());
        etAddress.setSelection(etAddress.getText().length());
    }

    private void initView() {
        titleView = (CommonTitleView) findViewById(R.id.title_view);
        titleView.setOnLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSet = (Button) findViewById(R.id.btn_set);
        etAddress = (EditText) findViewById(R.id.et_address);
        lvHistory = (ListView) findViewById(R.id.lv_history);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etAddress.getText().toString())){
                    Toast.makeText(PageChangeAddress.this, "请输入地址", Toast.LENGTH_SHORT).show();
                }
                else{
                    TestKitManager.getInstance().getOnAddressChangeListener().onAddressChange(etAddress.getText().toString());
                    TestKitManager.getInstance().setCurrentAddress(etAddress.getText().toString());
                    saveAddress(etAddress.getText().toString());
                    onBackPressed();
                }
            }
        });
    }

    private class AddressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return historyList == null ? 0 : historyList.size();
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
            if (convertView == null) {
                vh = new FileNameViewHolder();
                convertView = LayoutInflater.from(PageChangeAddress.this).inflate(R.layout.address_item, null);
                vh.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
                convertView.setTag(vh);
            } else {
                vh = (FileNameViewHolder) convertView.getTag();
            }
            vh.tvAddress.setText(historyList.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestKitManager.getInstance().getOnAddressChangeListener().onAddressChange(historyList.get(position));
                    TestKitManager.getInstance().setCurrentAddress(historyList.get(position));
                    onBackPressed();
                }
            });
            return convertView;
        }

        class FileNameViewHolder {
            TextView tvAddress;
        }
    }

    private void saveAddress(String address) {
        if(TextUtils.isEmpty(historyStr)){
            SpUtil.saveSharedPreferences(PageChangeAddress.this, KEY_ADDRESS, address);
        }
        else{
            SpUtil.saveSharedPreferences(PageChangeAddress.this, KEY_ADDRESS, historyStr + "," + address);
        }
    }
}
