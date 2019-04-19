package com.canvasseries.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    private Paint paint;
    private Path path;
    private int VIEW_WIDTH = 700;
    private int VIEW_HEIGHT = 1200;
    //定义一个内存中图片，将它作为缓冲区
    private Bitmap cacheBitmap;
    //定义缓冲区 Cache 的Canvas 对象
    Canvas cacheCanvas = null;

    private float preX;
    private float preY;

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //创建一个与该View相同大小的缓冲区
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        //创建缓冲区Cache的Canvas对象
        cacheCanvas = new Canvas();
        path = new Path();
        //设置cacheCanvas将会绘制到内存的bitmap上
        cacheCanvas.setBitmap(cacheBitmap);
        cacheCanvas.drawColor(Color.GRAY);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setFlags(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(22);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        //将 cacheBitmap 绘制到该view
        canvas.drawBitmap(cacheBitmap, 0, 0, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动时间的发生位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX, preY, x, y);
                preX = x;
                preY = y;
                cacheCanvas.drawPath(path, paint);
                break;
            case MotionEvent.ACTION_UP:
                //这是调用了cacheBitmap的Canvas在绘制
//                cacheCanvas.drawPath(path, paint);
                path.reset();
                break;
        }
        invalidate();//在UI线程刷新View
        return true;
    }
}
