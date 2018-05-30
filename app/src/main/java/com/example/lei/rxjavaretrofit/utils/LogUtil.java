package com.example.lei.rxjavaretrofit.utils;

import android.util.Log;

import com.example.lei.rxjavaretrofit.Config;

public class LogUtil {

    public static void d(String tag, String msg) {
        if (Config.NEED_LOG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Config.NEED_LOG) {
            Log.e(tag, msg);
        }
    }
}
