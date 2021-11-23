package com.scc.module.webview.ui.customview;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.scc.lib.utils.MLog;

public class MyWebChromeClient extends WebChromeClient {
    @Override
    public void onReceivedTitle(WebView view, final String title) {
        super.onReceivedTitle(view, title);
        if (title.contains("404") || title.contains("500") || title.contains("Error")) {
        } else {
        }
    }

    @Override
    public boolean onJsAlert(WebView view, final String url, final String message,
                             JsResult result) {
        MLog.e("onJsAlert:", url);
        result.confirm();//这里必须调用，否则页面会阻塞造成假死
        return true;
    }
}
