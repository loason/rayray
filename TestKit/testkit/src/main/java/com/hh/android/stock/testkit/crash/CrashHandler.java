package com.hh.android.stock.testkit.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.hh.android.stock.testkit.manager.DirManager;
import com.hh.android.stock.testkit.util.DateUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常奔溃捕获检测并保存错误日志到本地
 * Created by litj on 2016/11/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private final String TAG = "CrashHandler";

    private Thread.UncaughtExceptionHandler defaultHandler;

    private static CrashHandler crashHandler;

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    /**
     * 初始化
     */
    public void init() {
        /* 获取系统默认的UncaughtException处理器 */
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        /* 设置该CrashHandler为程序的默认处理器 */
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        /* 保存日志文件 */
        saveCrashInfo2File(e);
        defaultHandler.uncaughtException(t, e);
        Log.e(TAG, e.getMessage());
    }

    /**
     * 保存错误信息到文件中
     * @param e
     */
    private void saveCrashInfo2File(Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        try {
            String time = DateUtil.getNowTime(DateUtil.DATA_STYLE_1);
            String fileName = time + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                FileOutputStream fos = new FileOutputStream(DirManager.getCrashLogDir() + File.separator + fileName);
                fos.write(result.getBytes());
                fos.close();
            }
        } catch (Exception exception) {
            Log.e(TAG, exception.getMessage());
        }
    }

}
