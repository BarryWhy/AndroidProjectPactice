package com.dell.androidprojectpactice.ui.diyview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 2016/7/28.
 */
public class CustomTitleView extends View {

    private String mTitleText; //文本
    private int mTitleColor; //文本颜色
    private int mTitleSize; //文本大小

    /*绘制时控制文本绘制范围*/
    private Paint mPaint;
    private Rect mRect;

    public CustomTitleView(Context context) {
        super(context);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
