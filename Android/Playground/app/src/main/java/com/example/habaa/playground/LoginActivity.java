package com.example.habaa.playground;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by habaa on 2018-04-19.
 */


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etEmail, etPwd;
    LoginRequest loginRequest;
    String loginCkeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPwd = findViewById(R.id.pwd);
        btnLogin = findViewById(R.id.login);
    }
    public void onClickLoginBtn(View v){
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        loginRequest = (LoginRequest) new LoginRequest().execute(StartActivity.serverUrl+"/login.do",email,pwd);

    }

    public class LoginRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            signUp(strings[0],strings[1],strings[2]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("loginCkeck -- "+loginCkeck);
            if(loginCkeck.equals("false")) {
                Toast.makeText(LoginActivity.this,"아이디 비밀번호를 다시 한 번 확인해주세요",Toast.LENGTH_LONG).show();
            } else if(loginCkeck.equals("true")){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

        }
        //JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String res;
        private void signUp(String url, String email, String pwd) {
            try {
                /*jsonDataObject.put("email",email);
                jsonDataObject.put("pwd",pwd);*/

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("pwd", pwd));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //----------------------------------------------------
                HttpResponse response = client.execute(post);
                System.out.println("Response -- -"+response);

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println("Buffered rd -- "+line);

                    JSONArray jsonArray = new JSONArray(line);


                    System.out.println("JSONArray -- "+jsonArray);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String isSuccess = jsonObject.getString("LoginSucess");
                        System.out.println(isSuccess+ " - Login - "+isSuccess);
                        loginCkeck=isSuccess;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

