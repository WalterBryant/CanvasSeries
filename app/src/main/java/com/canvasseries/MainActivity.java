package com.canvasseries;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.canvasseries.myrevealview.GallaryHorizonalScrollView;
import com.canvasseries.myrevealview.RevealDrawable;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private int[] mImgIds = new int[]{ //7个
            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline,

            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline
    };
    private int[] mImgIds_active = new int[]{
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active,
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active
    };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setContentView(new MyView(this));
//        setContentView(new MyClipImage(this));
//        setContentView(new DrawView(this));

        initDrable();

        hzv = findViewById(R.id.hsv);

        hzv.addContainerChildViews(revealDrawables);
    }

    private void initDrable() {
        revealDrawables = new Drawable[mImgIds.length];

        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable drawable = null;
            drawable = new RevealDrawable(
                    ContextCompat.getDrawable(this, mImgIds[i]),
                    ContextCompat.getDrawable(this, mImgIds_active[i]));

            revealDrawables[i] = drawable;
        }
    }

    public void Search(View view) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }
}

