package com.scc.lib.utils;

import android.app.Application;

import java.lang.reflect.Method;

/**
  * 创建人：帅次
  * 创建时间：2021/11/4
  * 功能：
  */
public class AppGlobalUtils {
    private static Application myApp;
    public static Application getApplication() {
        if(myApp==null){
            try {
                Method currentApplication = Class.forName("android.app.ActivityThread").
                        getDeclaredMethod("currentApplication");
                myApp = (Application) currentApplication.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myApp;
    }
}
