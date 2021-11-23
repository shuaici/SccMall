package com.scc.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ImmUtils {
    /**
     * 设置点击EditText以外区域隐藏键盘
     * @param view
     * @param context
     */
    public static void setCloseKeyboard(View view, final Context context)
    {
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (!(view instanceof EditText))
        {
            view.setOnTouchListener(new View.OnTouchListener()
            {
                public boolean onTouch(View v, MotionEvent event)
                {
                    try
                    {
                        InputMethodManager inputMethodManager = (InputMethodManager) context
                                .getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(
                                ((Activity) context).getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e)
                    {
                    }
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup)
        {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
            {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setCloseKeyboard(innerView,context);
            }
        }
    }

}
