package com.hh.android.stock.testkit.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.hh.android.stock.testkit.callback.OnAddressChangeListener;
import com.hh.android.stock.testkit.crash.CrashHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试工具管理器
 * Created by litj on 2016/11/22.
 */

public class TestKitManager {

    private final String TAG = "TestKitManager" ;

    public static final String SP_TEST_KIT = "sp_test_kit";

    private static TestKitManager instance = null;

    private String currentAddress;

    private OnAddressChangeListener onAddressChangeListener;

    public static TestKitManager getInstance(){
        if(instance == null){
            instance = new TestKitManager();
        }
        return instance;
    }

    public void init(Context context){
        CrashHandler.getInstance().init();
    }

    public void setOnAddressChangeListener(OnAddressChangeListener onAddressChangeListener, String currentAddress){
        this.onAddressChangeListener = onAddressChangeListener;
        this.currentAddress = currentAddress;
    }

    public OnAddressChangeListener getOnAddressChangeListener(){
        return onAddressChangeListener;
    }

    public String getCurrentAddress(){
        return currentAddress;
    }

    public void setCurrentAddress(String address){
        currentAddress = address;
    }

    /**
     * 获取版本信息
     */
    public Map<String, String> getVersionInfo(Context context){
        Map<String, String> info = new HashMap<>();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                info.put("versionName", versionName);
                info.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return info;
    }

    /**
     * 获取设备参数信息
     */
    public Map<String, String> getDeviceInfo() {
        Map<String, String> info = new HashMap<>();
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                info.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return info;
    }

}
