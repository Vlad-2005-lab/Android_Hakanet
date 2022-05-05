package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Resources r;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = this.getResources();
        getSupportActionBar().hide();
    }

    public void onClick(View view) throws InterruptedException {
        int size_221_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                286,
                r.getDisplayMetrics()
        );
        int size_75_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                75,
                r.getDisplayMetrics()
        );
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vidvig);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        TextView enter_or_exit = (TextView) findViewById(R.id.enter_or_exit);
        TextView messenger = (TextView) findViewById(R.id.messenger);
        TextView profile = (TextView) findViewById(R.id.profile111);
        if (flag) {
            enter_or_exit.setVisibility(View.GONE);
            messenger.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            imageButton.animate().rotation(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    enter_or_exit.setVisibility(View.VISIBLE);
                    messenger.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.VISIBLE);
                }
            }).start();
            linearLayout.animate()
                    .setDuration(250)
                    .translationY(size_221_dp).setStartDelay(0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(size_221_dp);//Здесь оставляем изменения после конца анимации
                        }
                    }).start();
            ResizeWidthAnimation anim = new ResizeWidthAnimation(linearLayout, size_75_dp * 3);
            anim.setDuration(250);
            anim.setStartOffset(250);
            linearLayout.startAnimation(anim);
        } else {
            enter_or_exit.setVisibility(View.GONE);
            messenger.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            ResizeWidthAnimation anim = new ResizeWidthAnimation(linearLayout, size_75_dp);
            anim.setDuration(250);
            linearLayout.startAnimation(anim);
            imageButton.animate().rotation(-360).setDuration(500).start();
            linearLayout.animate()
                    .setDuration(250).setStartDelay(250)
                    .translationY(-size_221_dp)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(-size_221_dp);//Здесь оставляем изменения после конца анимации
                        }
                    });
        }
        flag = !flag;
    }

    public void enter_click(View view){
        Toast.makeText(getApplicationContext(), "enter", Toast.LENGTH_SHORT).show();
    }

    public void messenger(View view){
        Toast.makeText(getApplicationContext(), "messenger", Toast.LENGTH_SHORT).show();
    }

    public void profile(View view){
        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
    }

    public class ResizeWidthAnimation extends Animation {
        private int mWidth;
        private int mStartWidth;
        private View mView;

        public ResizeWidthAnimation(View view, int width) {
            mView = view;
            mWidth = width;
            mStartWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

            mView.getLayoutParams().width = newWidth;
            mView.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}