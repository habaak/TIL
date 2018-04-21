package com.example.habaa.playground;

import android.content.Entity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String lon,lat,pageNum;
    MainRequest mainRequest;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        mainRequest = (MainRequest) new MainRequest().execute(StartActivity.serverUrl+"/mainView.do","37.505258","127.0287443","1");
    }
    public void clickGoPostContent(View v){
        Intent intent = new Intent(MainActivity.this,PostContentsActivity.class);
        startActivity(intent);
    }
    public void onClickShowJson(View v){

    }
    public class MainRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            getMainJson(strings[0],strings[1],strings[2],strings[3]);
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(json);
            /*System.out.println("loginCkeck -- "+loginCkeck);
            if(loginCkeck.equals("false")) {
                Toast.makeText(LoginActivity.this,"아이디 비밀번호를 다시 한 번 확인해주세요",Toast.LENGTH_LONG).show();
            } else if(loginCkeck.equals("true")){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }*/
        }



        private void getMainJson(String url,String lon, String lat, String pagenum){
            url+="?lon="+lon+"&lat="+lat+"&pagenum="+pagenum;
            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response = client.execute(get);
                HttpEntity resEntitiyGet = response.getEntity();
                if(resEntitiyGet != null){
                    json=EntityUtils.toString(resEntitiyGet);
                    System.out.print("RESPONSE -- "+ EntityUtils.toString(resEntitiyGet));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
            }

        }
    }
}
