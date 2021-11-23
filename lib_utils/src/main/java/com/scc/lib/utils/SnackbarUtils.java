package com.scc.lib.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class SnackbarUtils {
    //设置Snackbar背景颜色
    private static final int color_info = 0XFF2094F3;
    private static final int color_confirm = 0XFF4CB04E;
    private static final int color_warning = 0XFFFEC005;
    private static final int color_danger = 0XFFF44336;
    //工具类当前持有的Snackbar实例
    private static WeakReference<Snackbar> snackbarWeakReference;

    public SnackbarUtils() {
    }

    /**
     * 获取 mSnackbar
     *
     * @return
     */
    private static Snackbar getSnackbar() {
        if (snackbarWeakReference != null && snackbarWeakReference.get() != null) {
            return snackbarWeakReference.get();
        } else {
            return null;
        }
    }

    public static class Builder {
        private Context context;
        private View view;//寻找最外层视图
        private String message;//内容
        private int duration = Snackbar.LENGTH_SHORT;//默认短时间
        private int backgroundColor = 0XFF323232;//背景颜色
        private int messageColor;//内容颜色
        private int actionBtnColor;//按钮颜色
        private float alpha;//设置透明度
        private int paramsGravity = 100;//设置Snackbar显示的位置
        private int messageGravity = 100;//设置内容在Snackbar显示位置

        private String actionText;//用户交互按钮
        private View.OnClickListener actionListener;//用户交互按钮点击事件
        public Builder(@NonNull Context context, @NonNull View view, @NonNull String message) {
            this.context = context;
            this.view = view;
            this.message = message;
        }

        public Builder(Context context, @NonNull View view, @NonNull int resId) {
            this.context = context;
            this.view = view;
            this.message = context.getString(resId);
        }

        /**
         * 设置显示时长
         *
         * @param showTime Snackbar.LENGTH_INDEFINITE   从show()显示,直到它被关闭或显示另一个 Snackbar。
         *                 Snackbar.LENGTH_SHORT        短时间
         *                 Snackbar.LENGTH_LONG         长时间
         *                 自定义持续时间                  以毫秒为单位
         * @return
         */
        public Builder setDuration(int showTime) {
            this.duration = showTime;
            return this;
        }

        //设置背景颜色
        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

//        //设置背景颜色
//        public Builder setBackgroundColorRes(int resId) {
//            return this;
//        }

        //设置TextView(@+id/snackbar_text)的文字颜色
        public Builder setMessageColor(@ColorInt int resId) {
            messageColor = resId;
            return this;
        }

        //设置Button(@+id/snackbar_action)的字体颜色
        public Builder setActionBtnColor(@ColorInt int actionBtnColor) {
            this.actionBtnColor = actionBtnColor;
            return this;
        }
        //设置Action按钮
        public Builder setAction(@StringRes int actionText, View.OnClickListener listener) {
            this.actionText = context.getString(actionText);
            this.actionListener = listener;
            return this;
        }
        //设置Action按钮
        public Builder setAction(String actionText, View.OnClickListener listener) {
            this.actionText = actionText;
            this.actionListener = listener;
            return this;
        }
        //设置透明度
        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setGravityLayout(int gravity) {
            this.paramsGravity = gravity;
            return this;
        }

        public Builder setGravityMessage(int gravity) {
            this.messageGravity = gravity;
            return this;
        }

        public Snackbar create() {
            Snackbar snackbar = Snackbar.make(view, message, duration);
            View view = getSnackbar().getView();
            if (view == null) {
                return snackbar;
            }
            TextView snackbarText = view.findViewById(R.id.snackbar_text);
            Button snackbarAction = (Button) view.findViewById(R.id.snackbar_action);
            if (backgroundColor != 0) {
                getSnackbar().getView().setBackgroundColor(backgroundColor);
            }
            if (messageColor != 0) {
                snackbarText.setTextColor(messageColor);
            }
            if (actionBtnColor != 0) {
                snackbarAction.setTextColor(actionBtnColor);
            }
            if (alpha != 0) {
                alpha = alpha >= 1.0f ? 1.0f : (Math.max(0.0f, alpha));
                view.setAlpha(alpha);
            }
            if (paramsGravity != 100) {
                CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(view.getLayoutParams().width, view.getLayoutParams().height);
                params.gravity = paramsGravity;
                view.setLayoutParams(params);
            }
            if (messageGravity != 100) {
                //View.setTextAlignment需要SDK>=17
                snackbarText.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                snackbarText.setGravity(messageGravity);
                snackbarText.setMaxLines(1);
            }
            if (actionText != null) {
                snackbarAction.setText(actionText);
            }
            if (actionListener != null) {
                snackbarAction.setOnClickListener(actionListener);
            }
            snackbarWeakReference = new WeakReference<>(snackbar);
            return snackbar;
        }
    }

    /**
     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居中
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public SnackbarUtils messageCenter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (getSnackbar() != null) {
                TextView message = (TextView) getSnackbar().getView().findViewById(R.id.snackbar_text);
                //View.setTextAlignment需要SDK>=17
                message.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                message.setGravity(Gravity.CENTER);
                message.setMaxLines(1);
            }
        }
        return this;
    }

    /**
     * 获得TextViev实例
     */
    public TextView getTextView() {
        if (getSnackbar() != null) {
            return ((TextView) getSnackbar().getView().findViewById(R.id.snackbar_text));
        } else {
            return null;
        }
    }

//    /**
//     * 设置Snackbar显示的位置,当Snackbar和CoordinatorLayout组合使用的时候
//     *
//     * @param gravity
//     */
//    public SnackbarUtils gravityCoordinatorLayout(int gravity) {
//        if (getSnackbar() != null) {
//            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(getSnackbar().getView().getLayoutParams().width, getSnackbar().getView().getLayoutParams().height);
//            params.gravity = gravity;
//            getSnackbar().getView().setLayoutParams(params);
//        }
//        return this;
//    }
//
//    /**
//     * 设置按钮文字内容 及 点击监听
//     * {@link Snackbar#setAction(CharSequence, View.OnClickListener)}
//     *
//     * @param resId
//     * @param listener
//     * @return
//     */
//    public SnackbarUtils setAction(@StringRes int resId, View.OnClickListener listener) {
//        if (getSnackbar() != null) {
//            return setAction(getSnackbar().getView().getResources().getText(resId), listener);
//        } else {
//            return this;
//        }
//    }
//
//    /**
//     * 设置按钮文字内容 及 点击监听
//     * {@link Snackbar#setAction(CharSequence, View.OnClickListener)}
//     *
//     * @param text
//     * @param listener
//     * @return
//     */
//    public SnackbarUtils setAction(CharSequence text, View.OnClickListener listener) {
//        if (getSnackbar() != null) {
//            getSnackbar().setAction(text, listener);
//        }
//        return this;
//    }
//
//    /**
//     * 设置TextView(@+id/snackbar_text)左右两侧的图片
//     *
//     * @param leftDrawable
//     * @param rightDrawable
//     * @return
//     */
//    public SnackbarUtils leftAndRightDrawable(@Nullable @DrawableRes Integer leftDrawable, @Nullable @DrawableRes Integer rightDrawable) {
//        if (getSnackbar() != null) {
//            Drawable drawableLeft = null;
//            Drawable drawableRight = null;
//            if (leftDrawable != null) {
//                try {
//                    drawableLeft = getSnackbar().getView().getResources().getDrawable(leftDrawable.intValue());
//                } catch (Exception e) {
//                }
//            }
//            if (rightDrawable != null) {
//                try {
//                    drawableRight = getSnackbar().getView().getResources().getDrawable(rightDrawable.intValue());
//                } catch (Exception e) {
//                }
//            }
//            return leftAndRightDrawable(drawableLeft, drawableRight);
//        } else {
//            return this;
//        }
//    }
//
//    /**
//     * 设置TextView(@+id/snackbar_text)左右两侧的图片
//     *
//     * @param leftDrawable
//     * @param rightDrawable
//     * @return
//     */
//    public SnackbarUtils leftAndRightDrawable(@Nullable Drawable leftDrawable, @Nullable Drawable rightDrawable) {
//        if (getSnackbar() != null) {
//            TextView message = (TextView) getSnackbar().getView().findViewById(R.id.snackbar_text);
//            LinearLayout.LayoutParams paramsMessage = (LinearLayout.LayoutParams) message.getLayoutParams();
//            paramsMessage = new LinearLayout.LayoutParams(paramsMessage.width, paramsMessage.height, 0.0f);
//            message.setLayoutParams(paramsMessage);
//            message.setCompoundDrawablePadding(message.getPaddingLeft());
//            int textSize = (int) message.getTextSize();
//            Log.e("Jet", "textSize:" + textSize);
//            if (leftDrawable != null) {
//                leftDrawable.setBounds(0, 0, textSize, textSize);
//            }
//            if (rightDrawable != null) {
//                rightDrawable.setBounds(0, 0, textSize, textSize);
//            }
//            message.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
//            LinearLayout.LayoutParams paramsSpace = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
//            ((Snackbar.SnackbarLayout) getSnackbar().getView()).addView(new Space(getSnackbar().getView().getContext()), 1, paramsSpace);
//        }
//        return this;
//    }
//
//
//    /**
//     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居右
//     *
//     * @return
//     */
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public SnackbarUtils messageRight() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            if (getSnackbar() != null) {
//                TextView message = (TextView) getSnackbar().getView().findViewById(R.id.snackbar_text);
//                //View.setTextAlignment需要SDK>=17
//                message.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
//                message.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
//            }
//        }
//        return this;
//    }
//
//    /**
//     * 向Snackbar布局中添加View(Google不建议,复杂的布局应该使用DialogFragment进行展示)
//     *
//     * @param layoutId 要添加的View的布局文件ID
//     * @param index
//     * @return
//     */
//    public SnackbarUtils addView(int layoutId, int index) {
//        if (getSnackbar() != null) {
//            //加载布局文件新建View
//            View addView = LayoutInflater.from(getSnackbar().getView().getContext()).inflate(layoutId, null);
//            return addView(addView, index);
//        } else {
//            return this;
//        }
//    }
//
//    /**
//     * 向Snackbar布局中添加View(Google不建议,复杂的布局应该使用DialogFragment进行展示)
//     *
//     * @param addView
//     * @param index
//     * @return
//     */
//    public SnackbarUtils addView(View addView, int index) {
//        if (getSnackbar() != null) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//设置新建布局参数
//            //设置新建View在Snackbar内垂直居中显示
//            params.gravity = Gravity.CENTER_VERTICAL;
//            addView.setLayoutParams(params);
//            ((Snackbar.SnackbarLayout) getSnackbar().getView()).addView(addView, index);
//        }
//        return this;
//    }
//
//    /**
//     * 设置Snackbar布局的外边距
//     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
//     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
//     *
//     * @param margin
//     * @return
//     */
//    public SnackbarUtils margins(int margin) {
//        if (getSnackbar() != null) {
//            return margins(margin, margin, margin, margin);
//        } else {
//            return this;
//        }
//    }
//
//    /**
//     * 设置Snackbar布局的外边距
//     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
//     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
//     *
//     * @param left
//     * @param top
//     * @param right
//     * @param bottom
//     * @return
//     */
//    public SnackbarUtils margins(int left, int top, int right, int bottom) {
//        if (getSnackbar() != null) {
//            ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//            ((ViewGroup.MarginLayoutParams) params).setMargins(left, top, right, bottom);
//            getSnackbar().getView().setLayoutParams(params);
//        }
//        return this;
//    }
//
//    /**
//     * 经试验发现:
//     *      执行过{@link SnackbarUtils#backColor(int)}后:background instanceof ColorDrawable
//     *      未执行过{@link SnackbarUtils#backColor(int)}:background instanceof GradientDrawable
//     * @return
//     */
//    /*
//    public SnackbarUtils radius(){
//        Drawable background = snackbarWeakReference.get().getView().getBackground();
//        if(background instanceof GradientDrawable){
//            Log.e("Jet","radius():GradientDrawable");
//        }
//        if(background instanceof ColorDrawable){
//            Log.e("Jet","radius():ColorDrawable");
//        }
//        if(background instanceof StateListDrawable){
//            Log.e("Jet","radius():StateListDrawable");
//        }
//        Log.e("Jet","radius()background:"+background.getClass().getSimpleName());
//        return new SnackbarUtils(mSnackbar);
//    }
//    */
//
//    /**
//     * 通过SnackBar现在的背景,获取其设置圆角值时候所需的GradientDrawable实例
//     *
//     * @param backgroundOri
//     * @return
//     */
//    private GradientDrawable getRadiusDrawable(Drawable backgroundOri) {
//        GradientDrawable background = null;
//        if (backgroundOri instanceof GradientDrawable) {
//            background = (GradientDrawable) backgroundOri;
//        } else if (backgroundOri instanceof ColorDrawable) {
//            int backgroundColor = ((ColorDrawable) backgroundOri).getColor();
//            background = new GradientDrawable();
//            background.setColor(backgroundColor);
//        } else {
//        }
//        return background;
//    }
//
//    /**
//     * 设置Snackbar布局的圆角半径值
//     *
//     * @param radius 圆角半径
//     * @return
//     */
//    public SnackbarUtils radius(float radius) {
//        if (getSnackbar() != null) {
//            //将要设置给mSnackbar的背景
//            GradientDrawable background = getRadiusDrawable(getSnackbar().getView().getBackground());
//            if (background != null) {
//                radius = radius <= 0 ? 12 : radius;
//                background.setCornerRadius(radius);
//                getSnackbar().getView().setBackgroundDrawable(background);
//            }
//        }
//        return this;
//    }
//
//    /**
//     * 设置Snackbar布局的圆角半径值及边框颜色及边框宽度
//     *
//     * @param radius
//     * @param strokeWidth
//     * @param strokeColor
//     * @return
//     */
//    public SnackbarUtils radius(int radius, int strokeWidth, @ColorInt int strokeColor) {
//        if (getSnackbar() != null) {
//            //将要设置给mSnackbar的背景
//            GradientDrawable background = getRadiusDrawable(getSnackbar().getView().getBackground());
//            if (background != null) {
//                radius = radius <= 0 ? 12 : radius;
//                strokeWidth = strokeWidth <= 0 ? 1 : (strokeWidth >= getSnackbar().getView().findViewById(R.id.snackbar_text).getPaddingTop() ? 2 : strokeWidth);
//                background.setCornerRadius(radius);
//                background.setStroke(strokeWidth, strokeColor);
//                getSnackbar().getView().setBackgroundDrawable(background);
//            }
//        }
//        return this;
//    }
//
//    /**
//     * 计算单行的Snackbar的高度值(单位 pix)
//     *
//     * @return
//     */
//    private int calculateSnackBarHeight() {
//        /*
//        <TextView
//                android:id="@+id/snackbar_text"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:layout_weight="1"
//                android:paddingTop="@dimen/design_snackbar_padding_vertical"
//                android:paddingBottom="@dimen/design_snackbar_padding_vertical"
//                android:paddingLeft="@dimen/design_snackbar_padding_horizontal"
//                android:paddingRight="@dimen/design_snackbar_padding_horizontal"
//                android:textAppearance="@style/TextAppearance.Design.Snackbar.Message"
//                android:maxLines="@integer/design_snackbar_text_max_lines"
//                android:layout_gravity="center_vertical|left|start"
//                android:ellipsize="end"
//                android:textAlignment="viewStart"/>
//        */
//        //文字高度+paddingTop+paddingBottom : 14sp + 14dp*2
//        int SnackbarHeight = ScreenUtils.dp2px(getSnackbar().getView().getContext(), 28) + ScreenUtils.sp2px(getSnackbar().getView().getContext(), 14);
//        Log.e("Jet", "直接获取MessageView高度:" + getSnackbar().getView().findViewById(R.id.snackbar_text).getHeight());
//        return SnackbarHeight;
//    }
//
//    /**
//     * 设置Snackbar显示在指定View的上方
//     * 注:暂时仅支持单行的Snackbar,因为{@link SnackbarUtils#calculateSnackBarHeight()}暂时仅支持单行Snackbar的高度计算
//     *
//     * @param targetView     指定View
//     * @param contentViewTop Activity中的View布局区域 距离屏幕顶端的距离
//     * @param marginLeft     左边距
//     * @param marginRight    右边距
//     * @return
//     */
//    public SnackbarUtils above(View targetView, int contentViewTop, int marginLeft, int marginRight) {
//        if (getSnackbar() != null) {
//            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
//            marginRight = marginRight <= 0 ? 0 : marginRight;
//            int[] locations = new int[2];
//            targetView.getLocationOnScreen(locations);
//            Log.e("Jet", "距离屏幕左侧:" + locations[0] + "==距离屏幕顶部:" + locations[1]);
//            int snackbarHeight = calculateSnackBarHeight();
//            Log.e("Jet", "Snackbar高度:" + snackbarHeight);
//            //必须保证指定View的顶部可见 且 单行Snackbar可以完整的展示
//            if (locations[1] >= contentViewTop + snackbarHeight) {
//                gravityFrameLayout(Gravity.BOTTOM);
//                ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, getSnackbar().getView().getResources().getDisplayMetrics().heightPixels - locations[1]);
//                getSnackbar().getView().setLayoutParams(params);
//            }
//        }
//        return this;
//    }
//
//    //CoordinatorLayout
//    public SnackbarUtils aboveCoordinatorLayout(View targetView, int contentViewTop, int marginLeft, int marginRight) {
//        if (getSnackbar() != null) {
//            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
//            marginRight = marginRight <= 0 ? 0 : marginRight;
//            int[] locations = new int[2];
//            targetView.getLocationOnScreen(locations);
//            Log.e("Jet", "距离屏幕左侧:" + locations[0] + "==距离屏幕顶部:" + locations[1]);
//            int snackbarHeight = calculateSnackBarHeight();
//            Log.e("Jet", "Snackbar高度:" + snackbarHeight);
//            //必须保证指定View的顶部可见 且 单行Snackbar可以完整的展示
//            if (locations[1] >= contentViewTop + snackbarHeight) {
//                gravityCoordinatorLayout(Gravity.BOTTOM);
//                ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, getSnackbar().getView().getResources().getDisplayMetrics().heightPixels - locations[1]);
//                getSnackbar().getView().setLayoutParams(params);
//            }
//        }
//        return this;
//    }
//
//    /**
//     * 设置Snackbar显示在指定View的下方
//     * 注:暂时仅支持单行的Snackbar,因为{@link SnackbarUtils#calculateSnackBarHeight()}暂时仅支持单行Snackbar的高度计算
//     *
//     * @param targetView     指定View
//     * @param contentViewTop Activity中的View布局区域 距离屏幕顶端的距离
//     * @param marginLeft     左边距
//     * @param marginRight    右边距
//     * @return
//     */
//    public SnackbarUtils bellow(View targetView, int contentViewTop, int marginLeft, int marginRight) {
//        if (getSnackbar() != null) {
//            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
//            marginRight = marginRight <= 0 ? 0 : marginRight;
//            int[] locations = new int[2];
//            targetView.getLocationOnScreen(locations);
//            int snackbarHeight = calculateSnackBarHeight();
//            int screenHeight = ScreenUtils.getScreenHeight(getSnackbar().getView().getContext());
//            //必须保证指定View的底部可见 且 单行Snackbar可以完整的展示
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //为什么要'+2'? 因为在Android L(Build.VERSION_CODES.LOLLIPOP)以上,例如Button会有一定的'阴影(shadow)',阴影的大小由'高度(elevation)'决定.
//                //为了在Android L以上的系统中展示的Snackbar不要覆盖targetView的阴影部分太大比例,所以人为减小2px的layout_marginBottom属性.
//                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight + 2 <= screenHeight) {
//                    gravityFrameLayout(Gravity.BOTTOM);
//                    ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight + 2));
//                    getSnackbar().getView().setLayoutParams(params);
//                }
//            } else {
//                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight <= screenHeight) {
//                    gravityFrameLayout(Gravity.BOTTOM);
//                    ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight));
//                    getSnackbar().getView().setLayoutParams(params);
//                }
//            }
//        }
//        return this;
//    }
//
//    public SnackbarUtils bellowCoordinatorLayout(View targetView, int contentViewTop, int marginLeft, int marginRight) {
//        if (getSnackbar() != null) {
//            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
//            marginRight = marginRight <= 0 ? 0 : marginRight;
//            int[] locations = new int[2];
//            targetView.getLocationOnScreen(locations);
//            int snackbarHeight = calculateSnackBarHeight();
//            int screenHeight = ScreenUtils.getScreenHeight(getSnackbar().getView().getContext());
//            //必须保证指定View的底部可见 且 单行Snackbar可以完整的展示
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //为什么要'+2'? 因为在Android L(Build.VERSION_CODES.LOLLIPOP)以上,例如Button会有一定的'阴影(shadow)',阴影的大小由'高度(elevation)'决定.
//                //为了在Android L以上的系统中展示的Snackbar不要覆盖targetView的阴影部分太大比例,所以人为减小2px的layout_marginBottom属性.
//                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight + 2 <= screenHeight) {
//                    gravityCoordinatorLayout(Gravity.BOTTOM);
//                    ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight + 2));
//                    getSnackbar().getView().setLayoutParams(params);
//                }
//            } else {
//                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight <= screenHeight) {
//                    gravityCoordinatorLayout(Gravity.BOTTOM);
//                    ViewGroup.LayoutParams params = getSnackbar().getView().getLayoutParams();
//                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight));
//                    getSnackbar().getView().setLayoutParams(params);
//                }
//            }
//        }
//        return this;
//    }
//
//    /**
//     * 设置TextView文字Size
//     *
//     * @return
//     */
//    public SnackbarUtils textandactionSize(int textSize, int actionSize) {
//        if (getSnackbar() != null) {
//            TextView message = (TextView) getSnackbar().getView().findViewById(R.id.snackbar_text);
//            Button action = (Button) getSnackbar().getView().findViewById(R.id.snackbar_action);
//            message.setTextSize(textSize);
//            action.setTextSize(actionSize);
//        }
//        return this;
//    }
//
//    /**
//     * 设置TextView文字Size
//     *
//     * @return
//     */
//    public SnackbarUtils textSize(int textSize) {
//        if (getSnackbar() != null) {
//            TextView message = (TextView) getSnackbar().getView().findViewById(R.id.snackbar_text);
//            message.setTextSize(textSize);
//        }
//        return this;
//    }
//
//    /**
//     * 设置TextView文字Size
//     *
//     * @return
//     */
//    public SnackbarUtils actionSize(int actionSize) {
//        if (getSnackbar() != null) {
//            Button action = (Button) getSnackbar().getView().findViewById(R.id.snackbar_action);
//            action.setTextSize(actionSize);
//        }
//        return this;
//    }
//
//
//    /**
//     * 显示 mSnackbar
//     */
//    public void show() {
//        Log.e("Jet", "show()");
//        if (getSnackbar() != null) {
//            Log.e("Jet", "show");
//            getSnackbar().show();
//        } else {
//            Log.e("Jet", "已经被回收");
//        }
//    }
}
