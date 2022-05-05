package com.example.test;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Students  extends AppCompatActivity {

    Resources r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);
        r = this.getResources();
        getSupportActionBar().hide();
    }
}
