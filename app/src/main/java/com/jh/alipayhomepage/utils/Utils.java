package com.jh.alipayhomepage.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.jh.alipayhomepage.R;

import java.lang.reflect.Field;

public class Utils {

    /**
     * 沉浸式状态栏
     *
     * @return 是否成功
     */
    public static boolean immerseStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return true;
        }
        return false;
    }


    /**
     * 获取状态栏的高度
     */
    @SuppressLint("PrivateApi")
    public static int getStatusBarHeight(Context context){
        Class<?> c;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 点击波纹效果
     */
    public static void setBorderlessBackground(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            int []attrs = new int[]{R.attr.selectableItemBackgroundBorderless};
            TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            view.setBackgroundResource(backgroundResource);
            typedArray.recycle();
        }
    }

    /**
     * dp和像素转换
     */
    public static int dp2px(Context context, float dpValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * m + 0.5f);
    }


}
