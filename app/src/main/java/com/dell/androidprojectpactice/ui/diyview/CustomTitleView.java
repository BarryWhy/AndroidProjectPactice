package com.dell.androidprojectpactice.ui.diyview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.dell.androidprojectpactice.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by dell on 2016/7/28.
 */
public class CustomTitleView extends TextView {

    private int POINT_NUM = 200; //点点的数量
    private int LINE_NUM = 4; //线的数量

    private String mTitleText; //文本
    private int mTitleColor; //文本颜色
    private int mTitleSize; //文本大小
    private int mTitleBackground; //背景颜色

    /*绘制时控制文本绘制范围*/
    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();

    public CustomTitleView(Context context)
    {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*
        * 获取自定义的样式属性
        * */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText: {
                    mTitleText = typedArray.getString(attr);
                    break;
                }
                case R.styleable.CustomTitleView_titleSize: {
                    /*
                    * 默认设置为16sp，TypeValue也可以把sp转化为px
                    * */
                    mTitleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                }
                case R.styleable.CustomTitleView_titleColor: {
                    /*
                    * 设置默认文本颜色为黑色
                    * */
                    mTitleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                }
                case R.styleable.CustomTitleView_titleBackground: {
                    mTitleBackground = typedArray.getColor(attr, Color.WHITE);
                }
                default: {
                    break;
                }
            }
        }
        typedArray.recycle();

        /*
        * 获得绘制文本的宽和高
        * */
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
        * 设置宽度
        * */
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY: { //明确指定宽度了
                width = getPaddingLeft() + getPaddingRight() + widthSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//一般为WARP_CONTENT
                width = getPaddingLeft() + getPaddingRight() + mRect.width();
                break;
            }
            case MeasureSpec.UNSPECIFIED: {//想多大就多大
                break;
            }
        }

        /*
        * 设置高度
        * */
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY: {//明确指定高度了
                height = getPaddingTop() + getPaddingBottom() + heightSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//一般为WARP_CONTENT
                height = getPaddingTop() + getPaddingBottom() + mRect.height();
                break;
            }
            case MeasureSpec.UNSPECIFIED: {
                break;
            }
        }
        setMeasuredDimension(width, height);

        /*
        * 绑定点击监听事件
        * */
        this.setOnClickListener(view -> {
            mTitleText = randomText();
            postInvalidate();
        });
    }

    /*
    * 创建随机数
    * */
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : set)
        {
            sb.append("").append(i);
        }
        return sb.toString();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        mPaint.setColor(mTitleBackground);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //绘制文本
        mPaint.setColor(mTitleColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
        //绘制噪点
        drawNoisePoint(canvas);
    }

    //初始化噪点Paint
    @NonNull
    private Paint initNoisePaint() {
        @SuppressLint("DrawAllocation") Paint mTempPaint = new Paint();
        int rgb_r = (int) (Math.random() * 255) + 1;
        int rgb_g = (int) (Math.random() * 255) + 1;
        int rgb_b = (int) (Math.random() * 255) + 1;
        mTempPaint.setAntiAlias(true);
        mTempPaint.setTextSize(15);
        mTempPaint.setStrokeWidth(3);
        mTempPaint.setARGB(255, rgb_r, rgb_g, rgb_b);
        return mTempPaint;
    }

    //绘制验证码的噪点
    private void drawNoisePoint(Canvas canvas) {
        final int height = getHeight();
        final int width = getWidth();
        //绘制线
        int [] line;
        for(int i = 0; i < LINE_NUM; i ++)
        {
            Paint mTempPaint = initNoisePaint();
            line = getLineCoordinate(height, width);
            canvas.drawLine(line[0], line[1], line[2], line[3], mTempPaint);
        }
        // 绘制小圆点
        int [] point;
        for(int i = 0; i < POINT_NUM; i ++)
        {
            Paint mTempPaint = initNoisePaint();
            point = getPointCoordinate(height, width);
            canvas.drawCircle(point[0], point[1], 2, mTempPaint);
        }
    }

    //获取线坐标
    public static int[] getLineCoordinate(int height, int width)
    {
        int [] lineXY = {0,0,0,0}; //线的始末点坐标
        for(int i = 0; i < 4; i+=2)
        {
            lineXY[i] = (int) (Math.random() * width);
            lineXY[i + 1] = (int) (Math.random() * height);
        }
        return lineXY;
    }

    //获取小点点圆心坐标
    public static int[] getPointCoordinate(int height, int width)
    {
        int [] circleCenterXY = {0,0}; //圆心坐标
        circleCenterXY[0] = (int) (Math.random() * width);
        circleCenterXY[1] = (int) (Math.random() * height);
        return circleCenterXY;
    }
}
