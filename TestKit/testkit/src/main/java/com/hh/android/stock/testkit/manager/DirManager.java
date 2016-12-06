package com.hh.android.stock.testkit.manager;

import android.os.Environment;

import java.io.File;

/**
 * 目录管理
 * Created by litj on 2016/11/23.
 */

public class DirManager {

    public static final String ROOT_NAME = "TestKit";
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory() + File.separator + ROOT_NAME;

    /**
     * 得到奔溃日志目录
     * @return 返回奔溃日志目录
     */
    public static String getCrashLogDir() {
        try {
            String dirPath = ROOT_PATH + File.separator + "CrashLog";
            File tmpDir = new File(dirPath);
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }
            return tmpDir.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

}
