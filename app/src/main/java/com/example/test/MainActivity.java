package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class
MainActivity extends AppCompatActivity {
    Resources r;
    boolean flag = true;
    int count = 0;
    WebView webview;
    LinearLayout i1;
    LinearLayout i2;
    LinearLayout i3;
    LinearLayout i4;
    LinearLayout i5;
    LinearLayout i6;
    LinearLayout i7;
    LinearLayout i8;
    LinearLayout i9;
    LinearLayout i10;
    LinearLayout i11;
    LinearLayout i12;
    LinearLayout i13;

    public void all_disabled(){
        i1.setBackgroundResource(R.drawable.circle);
        i2.setBackgroundResource(R.drawable.circle);
        i3.setBackgroundResource(R.drawable.circle);
        i4.setBackgroundResource(R.drawable.circle);
        i5.setBackgroundResource(R.drawable.circle);
        i6.setBackgroundResource(R.drawable.circle);
        i7.setBackgroundResource(R.drawable.circle);
        i8.setBackgroundResource(R.drawable.circle);
        i9.setBackgroundResource(R.drawable.circle);
        i10.setBackgroundResource(R.drawable.circle);
        i11.setBackgroundResource(R.drawable.circle);
        i12.setBackgroundResource(R.drawable.circle);
        i13.setBackgroundResource(R.drawable.circle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = this.getResources();
        getSupportActionBar().hide();

        webview = (WebView) findViewById(R.id.web_view);
        webview.loadUrl("file:///android_res/raw/infa.html");
//      #0231A1
        ImageButton left = (ImageButton) findViewById(R.id.imageButton6);
        left.setColorFilter(Color.argb(255, 2, 49, 241));
        ImageButton right = (ImageButton) findViewById(R.id.imageButton7);
        right.setColorFilter(Color.argb(255, 2, 49, 241));

        i1 = (LinearLayout) findViewById(R.id.home_i0);
        i2 = (LinearLayout) findViewById(R.id.home_i1);
        i3 = (LinearLayout) findViewById(R.id.home_i2);
        i4 = (LinearLayout) findViewById(R.id.home_i3);
        i5 = (LinearLayout) findViewById(R.id.home_i4);
        i6 = (LinearLayout) findViewById(R.id.home_i5);
        i7 = (LinearLayout) findViewById(R.id.home_i6);
        i8 = (LinearLayout) findViewById(R.id.home_i7);
        i9 = (LinearLayout) findViewById(R.id.home_i8);
        i10 = (LinearLayout) findViewById(R.id.home_i9);
        i11 = (LinearLayout) findViewById(R.id.home_i10);
        i12 = (LinearLayout) findViewById(R.id.home_i11);
        i13 = (LinearLayout) findViewById(R.id.home_i12);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        ImageButton bt = (ImageButton) findViewById(R.id.imageButton2);
        TextView tv = (TextView) findViewById(R.id.textView4);
        bt.setColorFilter(Color.argb(255, 255, 100, 0));
        tv.setTextColor(Color.argb(255, 255, 100, 0));

        ContentValues cv = new ContentValues();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            ImageView bt1 = (ImageView) findViewById(R.id.imageView7);
            bt1.setImageResource(R.drawable.ic_exit);
            TextView vieww = (TextView) findViewById(R.id.enter_or_exit);
            vieww.setText(R.string.exit);
        }
    }

    public void onClick(View view) throws InterruptedException {
        int size_221_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                205,
                r.getDisplayMetrics()
        );
        int size_75_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                55,
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

    public void previous(View view){
        count--;
        all_disabled();
        if (count < 0){
            count = 12;
        }
        switch (count){
            case 0:
                i1.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/infa.html");
                break;
            case 1:
                i2.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/technopark.html");
                break;
            case 2:
                i3.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/kvantorium.html");
                break;
            case 3:
                i4.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/avtokvantum.html");
                break;
            case 4:
                i5.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/hitech.html");
                break;
            case 5:
                i6.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/aerokvantum.html");
                break;
            case 6:
                i7.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/geokvantum.html");
                break;
            case 7:
                i8.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/robokvantum.html");
                break;
            case 8:
                i9.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/kosmovant.html");
                break;
            case 9:
                i10.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/laserkvantum.html");
                break;
            case 10:
                i11.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/itkvantum.html");
                break;
            case 11:
                i12.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/nejrokvantum.html");
                break;
            case 12:
                i13.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/svedenija.html");
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void next(View view){
        count++;
        all_disabled();
        if (count > 12){
            count = 0;
        }
        switch (count){
            case 0:
                i1.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/infa.html");
                break;
            case 1:
                i2.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/technopark.html");
                break;
            case 2:
                i3.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/kvantorium.html");
                break;
            case 3:
                i4.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/avtokvantum.html");
                break;
            case 4:
                i5.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/hitech.html");
                break;
            case 5:
                i6.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/aerokvantum.html");
                break;
            case 6:
                i7.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/geokvantum.html");
                break;
            case 7:
                i8.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/robokvantum.html");
                break;
            case 8:
                i9.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/kosmovant.html");
                break;
            case 9:
                i10.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/laserkvantum.html");
                break;
            case 10:
                i11.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/itkvantum.html");
                break;
            case 11:
                i12.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/nejrokvantum.html");
                break;
            case 12:
                i13.setBackgroundResource(R.drawable.circle_checked);
                webview.loadUrl("file:///android_res/raw/svedenija.html");
                break;
        }
    }

    public void info_0(View view){
        count = 0;
        all_disabled();
        i1.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/infa.html");
    }

    public void info_1(View view){
        count = 1;
        all_disabled();
        i2.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/technopark.html");
    }

    public void info_2(View view){
        count = 2;
        all_disabled();
        i3.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/kvantorium.html");
    }

    public void info_3(View view){
        count = 3;
        all_disabled();
        i4.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/avtokvantum.html");
    }

    public void info_4(View view){
        count = 4;
        all_disabled();
        i5.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/hitech.html");
    }

    public void info_5(View view){
        count = 5;
        all_disabled();
        i6.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/aerokvantum.html");
    }

    public void info_6(View view){
        count = 6;
        all_disabled();
        i7.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/geokvantum.html");
    }

    public void info_7(View view){
        count = 7;
        all_disabled();
        i8.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/robokvantum.html");
    }

    public void info_8(View view){
        count = 8;
        all_disabled();
        i9.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/kosmovant.html");
    }

    public void info_9(View view){
        count = 9;
        all_disabled();
        i10.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/laserkvantum.html");
    }

    public void info_10(View view){
        count = 10;
        all_disabled();
        i11.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/itkvantum.html");
    }

    public void info_11(View view){
        count = 11;
        all_disabled();
        i12.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/nejrokvantum.html");
    }

    public void info_12(View view){
        count = 12;
        all_disabled();
        i13.setBackgroundResource(R.drawable.circle_checked);
        webview.loadUrl("file:///android_res/raw/svedenija.html");
    }

    public void enter_click(View view){
        ContentValues cv = new ContentValues();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            MainActivity.this.deleteDatabase("myDB");
            ImageView bt1 = (ImageView) findViewById(R.id.imageView7);
            bt1.setImageResource(R.drawable.ic_login);
            TextView vieww = (TextView) findViewById(R.id.enter_or_exit);
            vieww.setText(R.string.enter);
        } else {
            Intent intent = new Intent(this, regenter.class);
            startActivity(intent);
        }
    }

    public void messenger(View view){
        Toast.makeText(getApplicationContext(), "messenger", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Allm.class);
        startActivity(intent);
        this.finish();
    }

    public void profile(View view){
        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
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

    public void go_students(View view){
        Intent intent = new Intent(this, Students.class);
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