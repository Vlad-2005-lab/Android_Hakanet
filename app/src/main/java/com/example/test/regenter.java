package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class regenter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regenter);
        getSupportActionBar().hide();
    }


    public void ent(View view){
        Intent intent = new Intent(this, Enter.class);
        startActivity(intent);
    }



    public void reg(View view){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}
