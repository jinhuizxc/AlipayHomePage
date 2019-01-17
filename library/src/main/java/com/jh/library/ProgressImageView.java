package com.jh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

public class ProgressImageView extends AppCompatImageView {

    private static final String TAG = ProgressImageView.class.getName();
    public int progressColor;

    private MaterialProgressDrawable mProgress;
    private boolean running = false;

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 1. xml配置信息获取
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.progress,
                0,
                0);
        progressColor = typedArray.getColor(R.styleable.progress_color, Color.BLACK);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate: ");

        mProgress = new MaterialProgressDrawable(getContext(), this);
        mProgress.setBackgroundColor(Color.WHITE);
        mProgress.setColorSchemeColors(progressColor);
        mProgress.setProgressRotation(1f);
        mProgress.showArrow(false);
        mProgress.setAlpha(255);
        setImageDrawable(mProgress);
    }

    public void startProgress() {
        if (!running){
            running = true;
            mProgress.start();
        }
    }
    public void stopProgress(){
        running = false;
        mProgress.stop();
    }

    public void setProgressColor(int progressColor){
        this.progressColor = progressColor;
        if (null != mProgress){
            mProgress.setColorSchemeColors(progressColor);
        }
    }

    public void setStartEndTrim(float start, float end){
        mProgress.setStartEndTrim(start, end);
    }

    public boolean isRunning() {
        return running;
    }
}
