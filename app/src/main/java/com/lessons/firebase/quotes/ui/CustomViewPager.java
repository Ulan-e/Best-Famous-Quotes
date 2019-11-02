package com.lessons.firebase.quotes.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean isPagingEnable;

    public CustomViewPager(@NonNull Context context) {
        super(context);

        this.isPagingEnable = true;
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isPagingEnable = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnable && super.onTouchEvent(ev);
    }

    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        return this.isPagingEnable && super.executeKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnable && super.onInterceptTouchEvent(ev);
    }

    public void setPagingEnable(boolean pagingEnable) {
        isPagingEnable = pagingEnable;
    }
}
