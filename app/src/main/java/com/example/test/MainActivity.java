package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Resources r;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = this.getResources();
    }

    public void onClick(View view) throws InterruptedException {
        int size_10_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                r.getDisplayMetrics()
        );
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vidvig);
        if (flag){
            linearLayout.animate()
                    .setDuration(500)
                    .translationY(size_10_dp * 20)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(size_10_dp * 20);//Здесь оставляем изменения после конца анимации
                        }
                    });
        } else {
            linearLayout.animate()
                    .setDuration(500)
                    .translationY(-size_10_dp * 20)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(-size_10_dp * 20);//Здесь оставляем изменения после конца анимации
                        }
                    });
        }
        flag = !flag;
        System.out.println(1);
    }
}