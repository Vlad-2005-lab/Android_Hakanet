package com.example.test;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Messages extends AppCompatActivity {
    Resources r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        r = this.getResources();
        getSupportActionBar().hide();
        LinearLayout sp = findViewById(R.id.lin);
        ScrollView sc = findViewById(R.id.mes);
        for (int i = 0; i < 100; ++i) {
            TextView t = new TextView(this);
            t.setText("aboba " + i);
            sp.addView(t);
        }
        sp.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            int last = 0;
            @Override
            public void onScrollChanged() {
                int y = sc.getScrollY();
                System.out.println(sp.getHeight());
                if (y == 0) {
                    for (int i = 0; i < 100; ++i) {
                        TextView t = new TextView(getApplicationContext());
                        t.setText("aboba " + i);
                        sp.addView(t, 0);
                    }
                    System.out.println("123");
                    System.out.println(sp.getHeight() - last);
//                    sp.setGravity(gr);'
                }
                System.out.println(y);
                last = sp.getHeight();
            }
        });
    }
}
