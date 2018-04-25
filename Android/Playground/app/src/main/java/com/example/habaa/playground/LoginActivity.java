package com.example.habaa.playground;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    public static String lat, lon;
    LocationManager manager;
    GPSListener gpsListener;


    public static String loginCkeck, spuidx, spname, spemail, sppwd, spage, spgender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = this.findViewById(R.id.email);
        etPwd = this.findViewById(R.id.pwd);
        btnLogin = this.findViewById(R.id.login);

        startLocationService();

    }

    public void onClickLoginBtn(View v) {
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();


        loginRequest = (LoginRequest) new LoginRequest().execute(StartActivity.serverUrl + "/login.do", email, pwd);

    }

    //============Login Network=================
    public class LoginRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            signUp(strings[0], strings[1], strings[2]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            System.out.println("loginCkeck -- " + loginCkeck);
            if (loginCkeck.equals("false")) {
                Toast.makeText(LoginActivity.this, "아이디 비밀번호를 다시 한 번 확인해주세요", Toast.LENGTH_LONG).show();
            } else if (loginCkeck.equals("true")) {
                Log.d("Login success", "id : " + spemail + " / uid : " + spuidx);

                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);//SharedPreferences객체 생성
                SharedPreferences.Editor editor = pref.edit();//데이터 저장을 위한 Editor 객체
                editor.putString("uidx", spuidx);
                editor.putString("pwd", sppwd);
                editor.putString("name", spname);
                editor.putString("age", spage);
                editor.putString("gender", spgender);
                editor.putBoolean("loginChecker", true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);
            }

        }

        //JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String res;

        private void signUp(String url, String email, String pwd) {
            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);


                Log.d("Location!!!!!!!!",lat+"/"+lon);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("pwd", pwd));
                nameValuePairs.add(new BasicNameValuePair("lat", lat));
                nameValuePairs.add(new BasicNameValuePair("lon", lon));
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
                        spuidx = jsonObject.getString("uidx");
                        spemail = jsonObject.getString("email");
                        spname = jsonObject.getString("name");
                        sppwd = jsonObject.getString("pwd");
                        spage = jsonObject.getString("age");
                        spgender = jsonObject.getString("gender");
                        System.out.println(isSuccess+ " - Login - "+isSuccess);
                        loginCkeck=isSuccess;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//============Location=============
private void startLocationService() {
    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    gpsListener = new GPSListener();
    long minTime = 1000;
    float minDistance = 0;
    try {
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
        Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            lat = String.valueOf(lastLocation.getLatitude());
            lon = String.valueOf(lastLocation.getLongitude());
            //textView.setText("내 위치 : " + latitude + ", " + longitude);
        }

    } catch (SecurityException ex) {
        ex.printStackTrace();
    }
}
    private class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            String msg = "Latitude : "+ lat + "\nLongitude:"+ lon; Log.i("GPSListener", msg);
            //textView.setText("내 위치는 : " + latitude + ", " + longitude);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            manager.removeUpdates(gpsListener);

        }
        public void onProviderDisabled(String provider) {

        } public void onProviderEnabled(String provider) {

        } public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

}

