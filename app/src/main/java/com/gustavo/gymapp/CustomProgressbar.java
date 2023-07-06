package com.gustavo.gymapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import javax.annotation.Nullable;

public class CustomProgressbar extends View {

    private final float stroke =40.0F;
    private final RectF background = new RectF();
    private final Paint bgPaint = new Paint();

    private final Paint paint = new Paint();
    private int progressBarValue = 25;
    private int progressBarColor;

    private int progressBarBg;
    private final Rect bounds = new Rect();
    private final RectF barArc = new RectF();


    public CustomProgressbar(Context context){
        super(context);
    }

    public CustomProgressbar(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomProgressbar);

        progressBarValue = typedArray.getInt(R.styleable.CustomProgressbar_my_progress,0);
        progressBarColor = typedArray.getColor(R.styleable.CustomProgressbar_my_progress_color,0);
        progressBarBg = typedArray.getColor(R.styleable.CustomProgressbar_my_progress_bg_color,Color.LTGRAY);

        typedArray.recycle();
    }

    public void setValue(int progressBarValue) {
        this.progressBarValue = progressBarValue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics metrics =getResources().getDisplayMetrics();
        float stroke = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.stroke,metrics);


        background.set(stroke,stroke,getWidth()-stroke,getHeight()-stroke);

        bgPaint.setColor(progressBarBg);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(60.0F);
        bgPaint.setAntiAlias(true);


        canvas.drawArc(background,0.0F,360.0F,false,bgPaint);

        paint.setColor(progressBarColor);
        paint.setStrokeWidth(stroke - 40.0f);
        paint.setDither(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        float progress = (360.0f/100) * progressBarValue;
        canvas.getClipBounds(bounds);
        barArc.set(stroke,stroke,bounds.right-stroke,bounds.bottom-stroke);
        canvas.drawArc(barArc,270.0F,progress,false,paint);




    }
}
