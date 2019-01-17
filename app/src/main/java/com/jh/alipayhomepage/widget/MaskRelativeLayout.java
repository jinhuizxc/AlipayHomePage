package com.jh.alipayhomepage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 遮罩RelativeLayout，alpha=0则交给底层View处理事件
 */
public class MaskRelativeLayout extends RelativeLayout {

    public MaskRelativeLayout(Context context) {
        super(context);
    }

    public MaskRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getAlpha() == 0){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getAlpha() == 0){
            return false;
        }
        return super.onTouchEvent(event);
    }
}
