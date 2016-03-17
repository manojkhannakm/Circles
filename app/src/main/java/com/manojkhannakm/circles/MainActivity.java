package com.manojkhannakm.circles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.manojkhannakm.circles.util.RandomUtils;
import com.manojkhannakm.circles.view.CircleLayout;
import com.manojkhannakm.circles.view.CircleTextView;
import com.manojkhannakm.circles.view.CircleView;

import java.util.ArrayList;

/**
 * @author Manoj Khanna
 */

public class MainActivity extends AppCompatActivity {

    private static final int CIRCLE_COUNT = 25;

    private CircleLayout mCircleLayout;
    private ArrayList<View> mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCircleLayout = (CircleLayout) findViewById(R.id.circle_layout_main);

        mViewList = new ArrayList<>(CIRCLE_COUNT);

        Button generateButton = (Button) findViewById(R.id.generate_button_main);
        generateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int maxStartDelay = 0;
                if (!mViewList.isEmpty()) {
                    int[] startDelays = new int[CIRCLE_COUNT];
                    for (int i = 0; i < CIRCLE_COUNT; i++) {
                        int startDelay = RandomUtils.nextInt(250);
                        startDelays[i] = startDelay;

                        if (startDelay > maxStartDelay) {
                            maxStartDelay = startDelay;
                        }
                    }

                    for (int i = 0; i < CIRCLE_COUNT; i++) {
                        hideView(mViewList.get(i), startDelays[i]);
                    }

                    maxStartDelay += 250;
                }

                mViewList = new ArrayList<>(CIRCLE_COUNT);

                for (int i = 0; i < CIRCLE_COUNT; i++) {
                    int[] colorResIds = new int[]{
                            R.color.red,
                            R.color.green,
                            R.color.blue,
                            R.color.orange,
                            R.color.violet
                    };
                    int color = ContextCompat.getColor(MainActivity.this, colorResIds[RandomUtils.nextInt(colorResIds.length - 1)]);
                    if (RandomUtils.nextBoolean()) {
                        addCircleView(RandomUtils.nextFloat(1.0f), RandomUtils.nextFloat(1.0f), RandomUtils.nextInt(25, 125), color);
                    } else {
                        addCircleTextView(RandomUtils.nextFloat(1.0f), RandomUtils.nextFloat(1.0f), RandomUtils.nextInt(25, 125), color, "Text");
                    }
                }

                for (View view : mViewList) {
                    showView(view, maxStartDelay + RandomUtils.nextInt(250));
                }
            }

        });
    }

    private int dipToPx(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    private void addCircleView(float xFactor, float yFactor, int sizeDip, int color) {
        CircleView circleView = new CircleView(this);
        circleView.setXFactor(xFactor);
        circleView.setYFactor(yFactor);
        circleView.setSize(dipToPx(sizeDip));
        circleView.setColor(color);
        circleView.setScaleX(0.0f);
        circleView.setScaleY(0.0f);
        mCircleLayout.addView(circleView);

        mViewList.add(circleView);
    }

    private void addCircleTextView(float xFactor, float yFactor, int sizeDip, int color, String text) {
        CircleTextView circleTextView = new CircleTextView(this);
        circleTextView.setXFactor(xFactor);
        circleTextView.setYFactor(yFactor);
        circleTextView.setSize(dipToPx(sizeDip));
        circleTextView.setColor(color);
        circleTextView.setText(text);
        circleTextView.setScaleX(0.0f);
        circleTextView.setScaleY(0.0f);
        mCircleLayout.addView(circleTextView);

        mViewList.add(circleTextView);
    }

    private void showView(View view, long startDelay) {
        AnimatorSet zoomInAnimatorSet = new AnimatorSet();
        zoomInAnimatorSet.play(ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f))
                .with(ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.0f));
        zoomInAnimatorSet.setStartDelay(startDelay);
        zoomInAnimatorSet.setDuration(250);
        zoomInAnimatorSet.setInterpolator(new OvershootInterpolator());
        zoomInAnimatorSet.start();
    }

    private void hideView(final View view, long startDelay) {
        AnimatorSet zoomOutAnimatorSet = new AnimatorSet();
        zoomOutAnimatorSet.play(ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.0f))
                .with(ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.0f));
        zoomOutAnimatorSet.setStartDelay(startDelay);
        zoomOutAnimatorSet.setDuration(250);
        zoomOutAnimatorSet.setInterpolator(new AnticipateInterpolator());
        zoomOutAnimatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mCircleLayout.removeView(view);
            }

        });
        zoomOutAnimatorSet.start();
    }

}
