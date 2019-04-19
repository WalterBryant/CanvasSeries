package com.canvasseries.searchview;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

public class ViewController implements MySearchView.DetachedViewLinstenner{

    private MySearchView mySearchView;

    private static final int DEFAULT_DURATION = 5600;// 动画默认执行时间

    public float mAnimatedValue;

    public ViewController() {

    }

    public void setSearchView(MySearchView searchView) {
        // MyserarchView和Controller互相绑定
        mySearchView = searchView;
        searchView.addController(this);
        searchView.setDetachedViewLinstenner(this);
    }

    public void open() {
        // 开始执行动画
        startAnimation(MySearchView.ANIMA_STATE_OPEN);
    }

    public void close() {
        startAnimation(MySearchView.ANIMA_STATE_CLOSE);
    }

    private void startAnimation(@MySearchView.AnimaState int state) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
        animator.setDuration(DEFAULT_DURATION);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (float) animation.getAnimatedValue();
                if (mySearchView != null) {
                    mySearchView.invalidate();
                }
            }
        });
        if (mySearchView != null) {
            mySearchView.setAnimaState(state);
        }
        animator.start();
    }

    @Override
    public void onDetachedView() {
        mySearchView = null;
    }
}
