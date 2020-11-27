package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import java.util.Random;

/**
 * Created by wing on 16/3/30.
 */
public class PathView extends CardiographView {
    private int r;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPath = new Path();
    }

    public static int getNum(int endNum){
        if(endNum > 0){
            Random random = new Random();
            return random.nextInt(endNum);
        }
        return 0;
    }

    private void drawPath(Canvas canvas) {
        // 重置path
        mPath.reset();
        r=getNum(10);
        //用path模拟一个心电图样式
        mPath.moveTo(0,mHeight/2);
        int tmp = 0;
        for(int i = 0;i<1000;i++) {
            mPath.lineTo(tmp+5, mHeight/2 -80-r);
            mPath.lineTo(tmp+15, mHeight / 2 + 80+r);
            mPath.lineTo(tmp+20, mHeight / 2);
            mPath.lineTo(tmp+25,mHeight / 2);
            mPath.lineTo(tmp+30, mHeight / 2-160-r);
            mPath.lineTo(tmp+40, mHeight / 2+160+r);
            mPath.lineTo(tmp+45, mHeight / 2);
            mPath.lineTo(tmp+100, mHeight / 2);
            tmp = tmp+100;
        }
        //设置画笔style
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath,mPaint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPath(canvas);
        scrollBy(1,0);
        postInvalidateDelayed(10);
    }
}