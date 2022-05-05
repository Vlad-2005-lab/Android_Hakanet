package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class register extends AppCompatActivity {
    Resources r;
    int size_10_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EditText mail = (EditText) findViewById(R.id.email);
        EditText login = (EditText) findViewById(R.id.login2);
        EditText password1 = (EditText) findViewById(R.id.passwordt1);
        EditText password2 = (EditText) findViewById(R.id.passwordt2);
        getSupportActionBar().hide();
        r = this.getResources();
        size_10_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                r.getDisplayMetrics()
        );
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    login.setBackgroundResource(R.drawable.gray_fockus);
                } else {
                    login.setBackgroundResource(R.drawable.gray);
                }
                login.setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
            }
        });
        password1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password1.setBackgroundResource(R.drawable.gray_fockus);
                } else {
                    password1.setBackgroundResource(R.drawable.gray);
                }
                password1.setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
            }
        });
        mail.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mail.setBackgroundResource(R.drawable.gray_fockus);
                } else {
                    mail.setBackgroundResource(R.drawable.gray);
                }
                mail.setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
            }
        });
        password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password2.setBackgroundResource(R.drawable.gray_fockus);
                } else {
                    password2.setBackgroundResource(R.drawable.gray);
                }
                password2.setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
            }
        });
    }

    public void Try_reg(View view) {
        String login = ((EditText) findViewById(R.id.login2)).getText().toString();
        String mail = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordt1)).getText().toString();
        String password_r = ((EditText) findViewById(R.id.passwordt2)).getText().toString();
        if (login.length() == 0){
            ((EditText) findViewById(R.id.login2)).setBackgroundResource(R.drawable.gray_red);
            ((EditText) findViewById(R.id.login2)).setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
        }
        if (mail.length() == 0){
            ((EditText) findViewById(R.id.email)).setBackgroundResource(R.drawable.gray_red);
            ((EditText) findViewById(R.id.email)).setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
        }
        if (password.length() == 0){
            ((EditText) findViewById(R.id.passwordt1)).setBackgroundResource(R.drawable.gray_red);
            ((EditText) findViewById(R.id.passwordt1)).setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
        }
        if (password_r.length() == 0){
            ((EditText) findViewById(R.id.passwordt2)).setBackgroundResource(R.drawable.gray_red);
            ((EditText) findViewById(R.id.passwordt2)).setPadding(size_10_dp, size_10_dp, size_10_dp, size_10_dp);
        }
        if (password.equals(password_r) && password.length() > 3) {
            AsyncRequest a = new AsyncRequest();
            String ans = a.doInBackground(login, password, mail);
            if (ans.equals("busy")) {
                ((EditText) findViewById(R.id.login)).setText("");
                Toast.makeText(this, "К сожалению, такой логин занят", Toast.LENGTH_LONG).show();
            } else if (ans.equals("invalid")){
                ((EditText) findViewById(R.id.email)).setText("");
                Toast.makeText(this, "Введите корректный email", Toast.LENGTH_LONG).show();
            } else if (ans.equals("mail")){
                ((EditText) findViewById(R.id.email)).setText("");
                Toast.makeText(this, "К сожалению, такой email занят", Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(this, Enter.class);
                startActivity(intent);
                this.finish();
            }
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

    static class AsyncRequest extends AsyncTask<String, Integer, String> {

        String domen = "4f74-178-72-68-144.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/create_user?l=" + arg[0] + "&p=" + arg[1] + "&m=" + arg[2];
            StringBuffer response;
            try {
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                return "error";
            }
        }
    }
}
