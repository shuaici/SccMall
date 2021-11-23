package com.scc.sccmall.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnticipateInterpolator;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scc.sccmall.databinding.ActivityAdvertiseBinding;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.AndroidViewModel;

public class AdvertiseActivity extends AppCompatActivity {
    ActivityAdvertiseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        binding = ActivityAdvertiseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvSplashJumpOver.setOnClickListener(v -> setARouterMainActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            extendDisplayTime();
            splashScreenCloseAnimation();
        }
    }

    private void splashScreenCloseAnimation() {
        //添加一个回调，当启动画面为应用内容设置动画时调用。
        getSplashScreen().setOnExitAnimationListener(splashScreenView -> {
            final ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.getHeight()

            );
            slideUp.setInterpolator(new AnticipateInterpolator());
            slideUp.setDuration(500);
            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    splashScreenView.remove();

                }
            });

            // Run your animation.
            slideUp.start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();
    }

    private long millisInFuture = 2000;
    private long countDownInterval = 1000;
    CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
        @Override
        public void onTick(long millisUntilFinished) {
            String time = String.valueOf((int) (millisUntilFinished / 1000) + 1);
            binding.tvSplashJumpOver.setText(String.format("%s，跳过", time));
        }

        @Override
        public void onFinish() {
            //倒计时结束
            setARouterMainActivity();
        }
    };

    private void setARouterMainActivity() {
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }

    //延长启动画面显示时间
    private void extendDisplayTime() {
        MyViewModel myViewModel = new MyViewModel(getApplication());
        // Set up an OnPreDrawListener to the root view.
        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        // Check if the initial data is ready.
                        if (myViewModel.isReady()) {
                            // The content is ready; start drawing.
                            content.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        } else {
                            // The content is not ready; suspend.
                            return false;
                        }
                    }
                });
    }

    public class MyViewModel extends AndroidViewModel {
        public MyViewModel(Application application) {
            super(application);
        }

        private long startUptimeMillis = SystemClock.uptimeMillis();

        public boolean isReady() {
            return SystemClock.uptimeMillis() - startUptimeMillis > 500;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
