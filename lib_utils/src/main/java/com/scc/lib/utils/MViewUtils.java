package com.scc.lib.utils;

import android.view.View;
import android.widget.TextView;

public class MViewUtils {

    public static void setVisibiVISIBLE(View view) {
        if (view != null && view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void setText(final TextView view, final String str) {
        if (view == null) {
            return;
        }
        try{
            if (!MStringUtils.isNullOrEmpty(str)) {
                if (str.indexOf("null") != -1) {
                    {
                        MStringUtils.replace(str, "null", "");
                    }
                }
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setText(str.replace("\\n", "\n"));
                    }
                });
            } else {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setText("");
                    }
                });
            }
        }catch (Exception e){
        }
    }

    public static String getText(TextView view) {
        if (view == null) {
            return "";
        }
        return view.getText().toString();
    }

    // 两次点击按钮之间的点击间隔不能少于200毫秒
    private static final int MIN_CLICK_DELAY_TIME = 300;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

}
