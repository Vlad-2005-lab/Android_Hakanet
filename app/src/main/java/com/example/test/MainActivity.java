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
import android.widget.ImageButton;
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
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        if (flag){
            imageButton.animate().rotation(360).setDuration(500);
            linearLayout.animate()
                    .setDuration(500)
                    .translationY(size_10_dp * 28)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(size_10_dp * 28);//Здесь оставляем изменения после конца анимации
                        }
                    }).start();
//            linearLayout.animate().scaleX(2).setStartDelay(500);
        } else {
            imageButton.animate().rotation(-360).setDuration(500);
            linearLayout.animate()
                    .setDuration(500)
                    .translationY(-size_10_dp * 28)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            linearLayout.setTranslationY(-size_10_dp * 28);//Здесь оставляем изменения после конца анимации
                        }
                    });
//            linearLayout.animate().scaleX((float) 0.5).setStartDelay(500);
        }
        flag = !flag;
        System.out.println(1);
    }
}