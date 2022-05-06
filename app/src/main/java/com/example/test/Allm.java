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
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Allm extends AppCompatActivity {
    Resources r;
    public static String[] words;
    LinearLayout l;
    public static String id;
    public static String cid;
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
        id = c.getString(2);
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

    public void to_chat(int id) {
        setContentView(R.layout.messages);
        xyz clas = new xyz();
        clas.execute(Integer.toString(id));
    }

    public void send(View view) {
        EditText f = findViewById(R.id.tex);
        String text = f.getText().toString();
        System.out.println("text");
        System.out.println(text);
        if (!text.equals("")){
            AsyncRequest5 ab = new AsyncRequest5();
            ab.execute(text, cid, id);
            f.setText("");
        } else{
            Toast.makeText(this, "Введите сообщение!!!!!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            System.out.println(v.getId());
            cid = Integer.toString(v.getId());
            to_chat(v.getId());
        }
    }

    class xyz extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... arg) {
            AsyncRequest3 a = new AsyncRequest3();
            String ans = a.doInBackground(arg[0]);
            words = ans.split(";");
            LinearLayout q = findViewById(R.id.lin);
            for (String s : words) {
                System.out.println(s);
                AsyncRequest4 ab = new AsyncRequest4();
                String mess = ab.doInBackground(s);
                String[] ok = mess.split(";");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayout lay = new LinearLayout(Allm.this.getApplicationContext());
                        TextView text = new TextView(Allm.this.getApplicationContext());
                        LinearLayout.LayoutParams li = new LinearLayout.LayoutParams(100, 100);
                        text.setText(ok[1] + ": " + ok[0]);
                        lay.addView(text);
                        q.addView(lay);
                    }
                });            }
            return "";
        }
    }

    class ass extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... arg) {
            AsyncRequest a = new AsyncRequest();
            String ans = a.doInBackground(arg[0]);
            words = ans.split(";");
            for (String s : words) {
                System.out.println(s);
                AsyncRequest2 ab = new AsyncRequest2();
                String name = ab.doInBackground(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayout lol = new LinearLayout(Allm.this.getApplicationContext());
                        TextView t = new TextView(Allm.this.getApplicationContext());
                        t.setText("name chata = " + name);
                        lol.addView(t);
                        LinearLayout.LayoutParams li = new LinearLayout.LayoutParams(100, 100);
                        lol.setId(Integer.parseInt(s));
                        lol.setOnClickListener(new MyListener());
                        lol.setLayoutParams(li);
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
            String url = "https://" + domen + "/get_chat?ci=" + arg[0];
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

    static class AsyncRequest3 extends AsyncTask<String, Integer, String> {

        String domen = "cb9c-178-178-95-130.eu.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/get_messages?ci=" + arg[0];
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

    static class AsyncRequest4 extends AsyncTask<String, Integer, String> {

        String domen = "cb9c-178-178-95-130.eu.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/get_message?mi=" + arg[0];
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

    static class AsyncRequest5 extends AsyncTask<String, Integer, String> {

        String domen = "cb9c-178-178-95-130.eu.ngrok.io";

        @Override
        protected String doInBackground(String... arg) {
            String url = "https://" + domen + "/send_message?m=" + arg[0] + "&ci=" + arg[1] + "&ui="+arg[2];
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
