package com.manojkhannakm.circles.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * @author Manoj Khanna
 */

public class CircleLayout extends RelativeLayout {

    public CircleLayout(Context context) {
        this(context, null);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                for (int i = 0; i < getChildCount(); i++) {
                    View view = getChildAt(i);
                    if (view instanceof CircleView) {
                        ((CircleView) view).setSize(((CircleView) view).getSize());
                        ((CircleView) view).setXFactor(((CircleView) view).getXFactor());
                        ((CircleView) view).setYFactor(((CircleView) view).getYFactor());
                    } else if (view instanceof CircleTextView) {
                        ((CircleTextView) view).setSize(((CircleTextView) view).getSize());
                        ((CircleTextView) view).setXFactor(((CircleTextView) view).getXFactor());
                        ((CircleTextView) view).setYFactor(((CircleTextView) view).getYFactor());
                    }
                }
            }

        });
    }

}
