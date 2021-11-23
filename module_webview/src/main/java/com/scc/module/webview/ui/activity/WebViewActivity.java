package com.scc.module.webview.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scc.lib.ui.BaseActivity;
import com.scc.lib.utils.ToastUtils;
import com.scc.module.webview.databinding.ActivityWebviewBinding;
import com.scc.module.webview.moudel.WebViewViewModel;
import com.scc.module.webview.ui.customview.MyWebChromeClient;
import com.scc.module.webview.ui.customview.MyWebViewClient;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

@Route(path = "/webview/WebViewActivity")
public class WebViewActivity extends BaseActivity<ActivityWebviewBinding, WebViewViewModel> {
    @Autowired
    public String url;
    @Autowired(name = "content")
    public String title;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入参数和服务(这里用到@Autowired所以要设置)
        //不使用自动注入,可不写，如CollectActivity没接收参数就没有设置
        ARouter.getInstance().inject(this);
        binding.btnBoom.setText(String.format("%s,你来啦", title));
        initWebView(binding.wbAbout, url);
        binding.btnBoom.setOnClickListener(v -> {
            ToastUtils.showMessage("我不想崩溃了");
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    //初始化WebView
    private void initWebView(WebView webView, final String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
        webSettings.setJavaScriptEnabled(true);//允许JS
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//设置屏幕自适应
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setAppCacheEnabled(false);
        // 设置可以使用localStorage
        webSettings.setDomStorageEnabled(false);

        webView.setEnabled(false);//把key event和 touch event都屏蔽掉了。
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onDestroy() {
        binding.wbAbout.setFocusable(false);
        binding.wbAbout.removeAllViews();
        try {
            binding.wbAbout.destroy();
        } catch (Throwable t) {
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (binding.wbAbout.getVisibility() == View.VISIBLE && keyCode == KeyEvent.KEYCODE_BACK) {
            if (binding.wbAbout.canGoBack()) {
                binding.wbAbout.goBack();
            } else {
                //返回到头，可以退出了
                finish();
            }
            return true;
        } else {
            //返回到头，可以退出了
            finish();
            return true;
        }
    }
}