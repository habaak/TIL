package com.example.habaa.driverapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.erise.habaak.driverapp.MainActivity;
import com.erise.habaak.driverapp.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etId, etPwd, etBusNum;
    LoginRequest loginRequest;

    public static String serverUrl = "http://192.168.0.24/first/admin/";
    public static String totaldistance, todaydistance, todaydrivetime, totaldrivetime, result_login, gender, resid, respwd, driveridx, name, age;
    public static String km, num, resbusidx, busfuel, platenum, year, bustype, service, busenergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = this.findViewById(R.id.etId);
        etPwd = this.findViewById(R.id.etPwd);
        etBusNum = this.findViewById(R.id.etBusNum);
        btnLogin = this.findViewById(R.id.btnLongin);

    }

    public void onClickLoginBtn(View v) {
        String busidx = etBusNum.getText().toString().trim();
        String id = etId.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        loginRequest = (LoginRequest) new LoginRequest().execute( serverUrl+ "driverlogin.do", busidx,id, pwd);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //============Login Network=================
    public class LoginRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Login(strings[0], strings[1], strings[2], strings[3]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            System.out.print("[loginCkeck]"+ result_login);
            if (result_login.equals("success")) {
                Log.d("[Login success]", "id : " + resid + "_password : " + respwd + "_busNumber : " + resbusidx);

                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);//SharedPreferences객체 생성
                SharedPreferences.Editor editor = pref.edit();//데이터 저장을 위한 Editor 객체
                //editor.putString("D_REGISTERDATE", d_registerdate);
                editor.putString("GENDER", gender);
                editor.putString("ID", resid);
                editor.putString("PWD", respwd);
                editor.putString("NAME", name);
                editor.putString("AGE", age);
                editor.putString("NUM", num);
                editor.putString("BUSIDX", resbusidx);
                editor.putString("PLATENUM", platenum);
                //editor.putString("REGIDATE", regidate);
                editor.putString("BUSTYPE", bustype);
                editor.putString("DRIVERIDX", driveridx);
                editor.putString("BUSENERGY", busenergy);

                editor.commit();

            } else {
                Toast.makeText(LoginActivity.this, "아이디 비밀번호를 다시 한 번 확인해주세요", Toast.LENGTH_LONG).show();
            }
        }

        //JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String res;

        private void Login(String url, String busidx, String id, String pwd) {
            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("busidx", busidx));
                nameValuePairs.add(new BasicNameValuePair("id", id));
                nameValuePairs.add(new BasicNameValuePair("pwd", pwd));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //----------------------------------------------------
                HttpResponse response = client.execute(post);

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    Log.i("[Login Buffered Reader]", line);

                    JSONArray jsonArray = new JSONArray(line);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        Log.i("[Login]", String.valueOf(jsonArray.length()));
                        if(i==0){
                            JSONObject driverJsonObject = jsonArray.getJSONObject(0);
                            Log.i("[Login]",driverJsonObject.toString());
                            totaldistance = driverJsonObject.getString("TOTALDISTANCE");
                            todaydistance = driverJsonObject.getString("TODAYDISTANCE");
                            todaydrivetime = driverJsonObject.getString("TODAYDRIVETIME");
                            totaldrivetime = driverJsonObject.getString("TOTALDRIVETIME");
                            //d_registerdate = driverJsonObject.getString("D_REGISTERDATE");
                            result_login = driverJsonObject.getString("result_login");
                            gender = driverJsonObject.getString("GENDER");
                            resid = driverJsonObject.getString("ID");
                            respwd = driverJsonObject.getString("PWD");
                            driveridx = driverJsonObject.getString("DRIVERIDX");
                            name = driverJsonObject.getString("NAME");
                            age = driverJsonObject.getString("AGE");
                        }
                        if(i==1){
                            JSONObject busJsonObject = jsonArray.getJSONObject(1);
                            Log.i("[Login]",busJsonObject.toString());
                            km = busJsonObject.getString("KM");
                            num = busJsonObject.getString("NUM");
                            resbusidx = busJsonObject.getString("BUSIDX");
                            busfuel = busJsonObject.getString("BUSFUEL");
                            platenum = busJsonObject.getString("PLATENUM");
                            year = busJsonObject.getString("YEAR");
                            //regidate = busJsonObject.getString("REGIDATE");
                            bustype = busJsonObject.getString("BUSTYPE");
                            service = busJsonObject.getString("SERVICE");
                            busenergy = busJsonObject.getString("BUSENERGY");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

