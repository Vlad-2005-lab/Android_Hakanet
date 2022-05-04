package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.number.IntegerWidth;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MulticastSocket;
import java.net.URL;

public class Enter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);
    }

    public void Try_enter(View view) {
        EditText e1 = (EditText) findViewById(R.id.login);
        EditText e2 = (EditText) findViewById(R.id.password);
        String login = e1.getText().toString();
        String password = e2.getText().toString();
        AsyncRequest a = new AsyncRequest();
        String ans = a.doInBackground(login, password);
        if (ans.contains("not ok")) {
            Toast.makeText(this, "Введены неверные данные", Toast.LENGTH_SHORT).show();
            e2.setText("");
        } else if (ans.contains("error")) {
            Toast.makeText(this, "Извините, сервер сейчас не доступен, попробуйте позже", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues cv = new ContentValues();
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
            cv.put("yes", "1");
            cv.put("my_id", Integer.parseInt(ans));
            db.insert("sq", null, cv);
            cv.clear();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
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

        String domen = "a339-178-72-68-143.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/check_user?l=" + arg[0] + "&p=" + arg[1];
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
