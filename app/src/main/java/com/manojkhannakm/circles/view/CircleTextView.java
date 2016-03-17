package com.manojkhannakm.circles.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manojkhannakm.circles.R;

/**
 * @author Manoj Khanna
 */

public class CircleTextView extends RelativeLayout {

    private static final float X_FACTOR = 0.5f;
    private static final float Y_FACTOR = 0.5f;
    private static final int SIZE = 64;
    private static final int COLOR = Color.BLACK;
    private static final CharSequence TEXT = "Text";

    private final ImageView mImageView;
    private final TextView mTextView;

    private float mXFactor = X_FACTOR, mYFactor = Y_FACTOR;
    private int mSize = dipToPx(SIZE), mColor = COLOR;
    private CharSequence mText = TEXT;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(getContext(), R.layout.circle_text_view, this);
        mImageView = (ImageView) view.findViewById(R.id.image_image_view_circle_text_view);
        mTextView = (TextView) view.findViewById(R.id.text_text_view_circle_text_view);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
            mXFactor = typedArray.getFloat(R.styleable.CircleTextView_circle_text_x_factor, X_FACTOR);
            mYFactor = typedArray.getFloat(R.styleable.CircleTextView_circle_text_y_factor, Y_FACTOR);
            mSize = typedArray.getDimensionPixelSize(R.styleable.CircleTextView_circle_text_size, dipToPx(SIZE));
            mColor = typedArray.getColor(R.styleable.CircleTextView_circle_text_color, COLOR);

            mText = typedArray.getText(R.styleable.CircleTextView_circle_text_text);
            if (mText == null) {
                mText = TEXT;
            }

            typedArray.recycle();
        }

        setSize(mSize);
        setColor(mColor);
        setText(mText);
    }

    private int dipToPx(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public float getXFactor() {
        return mXFactor;
    }

    public void setXFactor(float xFactor) {
        mXFactor = xFactor;

        RelativeLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
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

        RelativeLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
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

        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mImageView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = mSize;
            layoutParams.height = mSize;
            mImageView.setLayoutParams(layoutParams);
        }

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSize * 0.15f);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;

        Drawable drawable = DrawableCompat.wrap(mImageView.getDrawable());
        DrawableCompat.setTint(drawable, mColor);
        DrawableCompat.unwrap(drawable);
    }

    public CharSequence getText() {
        return mText;
    }

    public void setText(CharSequence text) {
        mText = text;

        mTextView.setText(mText);
    }

}
