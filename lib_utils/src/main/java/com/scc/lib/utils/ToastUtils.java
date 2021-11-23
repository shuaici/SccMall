package com.scc.lib.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


public class ToastUtils {
    private static Toast toast;
    public static void showMessage(String str) {
        if (!str.isEmpty()) {
            showMessage(str,0);
        }
    }
    public static void showMessage(int resId) {
        if (resId!=0) {
            showMessage(AppGlobalUtils.getApplication().getString(resId),0);
        }
    }
    public static void showMessage(int resId,int length) {
        if (resId!=0) {
            showMessage(AppGlobalUtils.getApplication().getString(resId),length);
        }
    }
    public static void showMessage(String str,int length) {
        if (!str.isEmpty()) {
            try {
                cancelToast();
                //只有mToast==null时才重新创建，否则只需更改提示文字
                if (toast == null) {
                    toast = Toast.makeText(AppGlobalUtils.getApplication(), str, length==0?Toast.LENGTH_SHORT:length);
                    toast.show();
                }
            } catch (Exception e) {
                //解决在子线程中调用Toast的异常情况处理
                MLog.e("ToastUtils:"+e.getMessage());
            }
        }
    }
    public static void showMessageSnackar(View view,String str) {
        if (!str.isEmpty()) {
            showMessageSnackar(view,str,0);
        }
    }
    public static void showMessageSnackar(View view,int resId) {
        if (resId!=0) {
            showMessageSnackar(view,AppGlobalUtils.getApplication().getString(resId),0);
        }
    }
    public static void showMessageSnackar(View view,String str,int length) {
        if (!str.isEmpty()) {
            try {
                new SnackbarUtils.Builder(AppGlobalUtils.getApplication().getApplicationContext(), view, str)
                        .setGravityLayout(Gravity.CENTER)
                        .setGravityMessage(Gravity.CENTER)
                        .create().show();
            } catch (Exception e) {
                //解决在子线程中调用Toast的异常情况处理
                MLog.e("ToastUtils:"+e.getMessage());
            }
        }
    }
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }
}
