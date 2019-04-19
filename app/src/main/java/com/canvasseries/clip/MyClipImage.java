package com.canvasseries.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.canvasseries.R;

public class MyClipImage extends View {

    private Bitmap mBitMap;
    private int clipWidth = 120;
    private int width;
    private int height;
    private Path mPath;
    private static final int CLIP_HEIGHT = 30;
    private Rect mRect;

    public MyClipImage(Context context) {
        super(context);
        init();
    }


    public MyClipImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyClipImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitMap = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy2);
        width = mBitMap.getWidth();
        height = mBitMap.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //  先画出裁剪区域
        int i = 0;
        while (i * CLIP_HEIGHT <= height) {
            if (i % 2 == 0) {
                //替换了Region.union方法
                mPath.addRect(new RectF(0, i * CLIP_HEIGHT, clipWidth, (i + 1) * CLIP_HEIGHT), Path.Direction.CCW);
            } else {
                mPath.addRect(new RectF(width, i * CLIP_HEIGHT, width - clipWidth, (i + 1) * CLIP_HEIGHT), Path.Direction.CCW);
            }
            i++;
        }
        //替换了canvas.clipRegion(region)
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitMap, 0, 0, new Paint());
        if (clipWidth > width) {
            return;
        }
        clipWidth += 50;
        postInvalidate();
    }
}
