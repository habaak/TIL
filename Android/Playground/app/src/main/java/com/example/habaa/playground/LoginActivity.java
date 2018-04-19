package com.example.habaa.playground;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by habaa on 2018-04-19.
 */


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etEmail, etPwd;
    LoginTask loginTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPwd = findViewById(R.id.pwd);
        btnLogin = findViewById(R.id.login);
        System.out.print("startLogin");
    }
    public void clickLoginBtn(View v){
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        loginTask = new LoginTask();
        loginTask.execute(email,pwd);
    }
    class LoginTask extends AsyncTask<String,String,String>{
        String url = "http://192.168.1.170/playground/login.do";
        HttpClient http = new DefaultHttpClient();
        ArrayList post = new ArrayList<>();// NameValuePair 변수명과 값을 함께 저장하는 객체
        //post 방식으로 전달할 값들
        HttpPost httpPost = new HttpPost(url);

        @Override
        protected String doInBackground(String... strings) {
            String email = strings[0];
            String pwd = strings[1];

            try{
                post.add(new BasicNameValuePair("email",email));
                post.add(new BasicNameValuePair("pwd",pwd));
                //url encoding이 필요한 값들(한글, 특수문자)
                UrlEncodedFormEntity request = new UrlEncodedFormEntity(post,"euc-kr");
                //HttpPost httpPost = new HttpPost(url);
                //post 방식으로 전달할 데이터 설정
                httpPost.setEntity(request);
               /* //post 방식으로 전송, 응답결과는 response로
                HttpResponse response=http.execute(httpPost);

                //response text를 스트링으로 변환
                String body= EntityUtils.toString(response.getEntity());
                // 스트링을 json으로
                JSONObject obj=new JSONObject(body);
                final String message= obj.getString("message");*/

            } catch (Exception e) {
                e.printStackTrace();
            }



            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            //post 방식으로 전송, 응답결과는 response로
            HttpResponse response= null;
            try {
                response = http.execute(httpPost);
                //response text를 스트링으로 변환
                String body= EntityUtils.toString(response.getEntity());
                // 스트링을 json으로
                JSONObject obj=new JSONObject(body);
                final String message= obj.getString("message");

                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

