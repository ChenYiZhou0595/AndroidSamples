package com.chen.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @Description: 这种方法是使用 margin 改变 view 位置，会影响其 parent view 下其他 view 的位置，
 *               该 view 需放在 parent 下的第一个，不然会出现显示问题
 * @Author: Chenyz
 * @Date: 2019/8/9 10:37
 */
public class LayoutParamsView extends View {

    private int lastX;

    private int lastY;

    public LayoutParamsView(Context context) {
        super(context);
    }

    public LayoutParamsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutParamsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LayoutParamsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
