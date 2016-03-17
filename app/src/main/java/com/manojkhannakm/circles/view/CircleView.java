package com.manojkhannakm.circles.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.manojkhannakm.circles.R;

/**
 * @author Manoj Khanna
 */

public class CircleView extends ImageView {

    private static final float X_FACTOR = 0.5f;
    private static final float Y_FACTOR = 0.5f;
    private static final int SIZE = 64;
    private static final int COLOR = Color.BLACK;

    private float mXFactor = X_FACTOR, mYFactor = Y_FACTOR;
    private int mSize = dipToPx(SIZE), mColor = COLOR;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setImageResource(R.drawable.image_image_view_circle_text_view);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
            mXFactor = typedArray.getFloat(R.styleable.CircleView_circle_x_factor, X_FACTOR);
            mYFactor = typedArray.getFloat(R.styleable.CircleView_circle_y_factor, Y_FACTOR);
            mSize = typedArray.getDimensionPixelSize(R.styleable.CircleView_circle_size, dipToPx(SIZE));
            mColor = typedArray.getColor(R.styleable.CircleView_circle_color, COLOR);
            typedArray.recycle();
        }

        setSize(mSize);
        setColor(mColor);
    }

    private int dipToPx(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public float getXFactor() {
        return mXFactor;
    }

    public void setXFactor(float xFactor) {
        mXFactor = xFactor;

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        RelativeLayout parent = (RelativeLayout) getParent();
        if (layoutParams != null && parent != null) {
            layoutParams.leftMargin = (int) (mXFactor * (parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) - mSize / 2.0f);
            setLayoutParams(layoutParams);
        }
    }

    public float getYFactor() {
        return mYFactor;
    }

    public void setYFactor(float yFactor) {
        mYFactor = yFactor;

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        RelativeLayout parent = (RelativeLayout) getParent();
        if (layoutParams != null && parent != null) {
            layoutParams.topMargin = (int) (mYFactor * (parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom()) - mSize / 2.0f);
            setLayoutParams(layoutParams);
        }
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = mSize;
            layoutParams.height = mSize;
            setLayoutParams(layoutParams);
        }
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;

        Drawable drawable = DrawableCompat.wrap(getDrawable());
        DrawableCompat.setTint(drawable, mColor);
        DrawableCompat.unwrap(drawable);
    }

}
