package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Allm extends AppCompatActivity {
    Resources r;
    public static String[] words;
    LinearLayout l;
    ScrollView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allm);
        r = this.getResources();
        getSupportActionBar().hide();
        ContentValues cv = new ContentValues();
        Enter.DBHelper dbHelper = new Enter.DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.query("sq", null, null, null, null, null, null);
        c.moveToNext();
        String id = c.getString(2);
        l = findViewById(R.id.ngrok);
        sc = findViewById(R.id.allmes);
        ass a = new ass();
        a.execute(id);
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

        String domen = "cb9c-178-178-95-130.eu.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/get_all_chats?i=" + arg[0];
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
    class ass extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... arg) {

            AsyncRequest a = new AsyncRequest();
            String ans = a.doInBackground(arg[0]);
            words = ans.split(";");
            for (String s : words){
                System.out.println(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayout lol = new LinearLayout(Allm.this.getApplicationContext());
                        TextView t = new TextView(Allm.this.getApplicationContext());
                        t.setText("id chata = " + s);
                        lol.addView(t);
                        l.addView(lol);
                    }
                });

            }
            return "";
        }
    }

    static class AsyncRequest2 extends AsyncTask<String, Integer, String> {

        String domen = "cb9c-178-178-95-130.eu.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/get_chat?i=" + arg[0];
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
