package com.example.test;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Teachers  extends AppCompatActivity {
    Resources r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers);
        r = this.getResources();
        getSupportActionBar().hide();
    }
}
