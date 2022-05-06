package com.example.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Students  extends AppCompatActivity {
    boolean flag = true;
    Resources r;
    boolean enter = false;
    int size_221_dp;
    int size_75_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);
        r = this.getResources();
        getSupportActionBar().hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        ImageButton bt = (ImageButton) findViewById(R.id.imageButton5111);
        TextView tv = (TextView) findViewById(R.id.textView7111);
        bt.setColorFilter(Color.argb(255, 255, 100, 0));
        tv.setTextColor(Color.argb(255, 255, 100, 0));

        WebView news = (WebView) findViewById(R.id.web_view_students);
        news.loadUrl("file:///android_res/raw/hui3.html");

        ContentValues cv = new ContentValues();
        MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
        ((LinearLayout) findViewById(R.id.profile_menu111)).setVisibility(View.GONE);
        ((LinearLayout) findViewById(R.id.messengers_menu111)).setVisibility(View.GONE);
        if (c.moveToFirst()) {
            ImageView bt1 = (ImageView) findViewById(R.id.imageView7111);
            bt1.setImageResource(R.drawable.ic_exit);
            TextView vieww = (TextView) findViewById(R.id.enter_or_exit111);
            vieww.setText(R.string.exit);
            ((LinearLayout) findViewById(R.id.profile_menu111)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.messengers_menu111)).setVisibility(View.VISIBLE);
            enter = true;
        }
    }

    public void onClick(View view) throws InterruptedException {
        size_221_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (enter ? 205 : 115),
                r.getDisplayMetrics()
        );
        size_75_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                55,
                r.getDisplayMetrics()
        );
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vidvig111);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton111);
        TextView enter_or_exit = (TextView) findViewById(R.id.enter_or_exit111);
        TextView messenger = (TextView) findViewById(R.id.messenger111);
        TextView profile = (TextView) findViewById(R.id.profile111111);
        if (flag) {
            enter_or_exit.setVisibility(View.GONE);
            messenger.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            if (!enter){
                ((LinearLayout) findViewById(R.id.profile_menu111)).setVisibility(View.GONE);
                ((LinearLayout) findViewById(R.id.messengers_menu111)).setVisibility(View.GONE);
            }
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
        ContentValues cv = new ContentValues();
        MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            this.deleteDatabase("myDB");
            ImageView bt1 = (ImageView) findViewById(R.id.imageView7111);
            bt1.setImageResource(R.drawable.ic_login);
            TextView vieww = (TextView) findViewById(R.id.enter_or_exit111);
            vieww.setText(R.string.enter);
            ((LinearLayout) findViewById(R.id.profile_menu111)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.messengers_menu111)).setVisibility(View.GONE);
            enter = false;
            size_221_dp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    115,
                    r.getDisplayMetrics()
            );
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.vidvig111);
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
        } else {
            Intent intent = new Intent(this, regenter.class);
            startActivity(intent);
        }
    }

    public void messenger(View view){
        Toast.makeText(getApplicationContext(), "messenger", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
    }

    public void profile(View view){
        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
    }

    public void go_home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void go_news(View view){
        Intent intent = new Intent(this, News.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void go_teachers(View view){
        Intent intent = new Intent(this, Teachers.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
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

    static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table sq ("
                    + "id integer primary key autoincrement,"
                    + "yes text," + "my_id integer" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
