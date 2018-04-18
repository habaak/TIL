package com.example.student.p593;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    LoginTask loginTask;
    LocationTask locationTask;
    boolean flag = true;
    AlertDialog dialog;
    AlertDialog.Builder alBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        alBuilder = new AlertDialog.Builder(MainActivity.this);

        new Thread(r).start();
        //ProgressDialog progressDialog;

    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            while(flag){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                locationTask = new LocationTask("http://70.12.114.132:8070/android/location.jsp");
                locationTask.execute(37.12,127.123);
            }
        }
    };


    public void clickBt(View v){
        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        if(id == null || pwd ==null || id.equals("") || pwd.equals("")){
            return;
        }
        loginTask = new LoginTask("http://70.12.114.132:8070/android/login.jsp");
        loginTask.execute(id.trim(),pwd.trim());
        //progressDialog =  new ProgressDialog(MainActivity.this);
    }


    class LoginTask extends AsyncTask<String,String,String>{
        String url;
        public LoginTask(){}
        BufferedReader br =null;
        public LoginTask(String url){
            this.url = url;
        }


        @Override
        protected void onPreExecute() {
            //progressDialog.setMessage("login");
            //progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0];
            String pwd = strings[1];
            url += "?id="+id+"&pwd="+pwd;
            //HTTP request

            StringBuilder sb = new StringBuilder();
            URL url;
            HttpURLConnection con=null;
            try{
                url = new URL(this.url);
                con = (HttpURLConnection) url.openConnection();
                if (con !=null){
                    con.setReadTimeout(10000);

                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept","*/*");
                    if (con.getResponseCode() != HttpURLConnection.HTTP_OK)
                        return  null;

                }

                br = new BufferedReader(new InputStreamReader(
                        con.getInputStream()
                    )
                );
                String line = null;
                while(true){
                    line = br.readLine();
                    if(line == null){
                        break;
                    }
                    sb.append(line);

                }

            }catch (Exception e){

                //Toast.makeText(MainActivity.this,"Connection Error"+e.getMessage(),Toast.LENGTH_LONG);
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                con.disconnect();
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.trim().equals("1")){
                Toast.makeText(MainActivity.this,"Login ok",Toast.LENGTH_SHORT).show();
            }else if(s.trim().equals("2")){
                Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"???",Toast.LENGTH_SHORT).show();
            }
        }
    }



    class LocationTask extends AsyncTask<Double,String,String> {
        String url;

        public LocationTask() {
        }

        public LocationTask(String url) {
            this.url = url;
        }
        @Override
        protected String doInBackground(Double... doubles) {
            double lat = doubles[0];
            double lon = doubles[1];

            url += "?lat="+lat+"&lon="+lon;

            //HTTP request

            StringBuffer sb = new StringBuffer();
            URL url;
            HttpURLConnection con=null;
            try{
                url = new URL(this.url);
                con = (HttpURLConnection) url.openConnection();
                if (con !=null){
                    con.setReadTimeout(30000);

                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept","*/*");
                    if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        return null;
                    }
                }
            }catch (Exception e){
                return e.getMessage();
            }finally {
                con.disconnect();
            }
            return "";
        }

    }

    @Override
    public void onBackPressed() {
        alBuilder.setTitle("Alert");
        alBuilder.setMessage("Are you finish app?");
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                flag=false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        alBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog = alBuilder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}