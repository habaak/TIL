package com.example.habaa.playground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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



    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//===============================SharedPreferences
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uidx = getResources().getString(Integer.parseInt("uidx"));

        tv = findViewById(R.id.textView);
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                100,
                1, mLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
        mainRequest = (MainRequest) new MainRequest().execute(StartActivity.serverUrl+"/mainView.do",lat,lon,uidx);
    }

    //사진올리기
    public void clickGoPostContent(View v){
        Intent intent = new Intent(MainActivity.this,PostContentsActivity.class);
        startActivity(intent);
    }
    //로그아웃
    public void clickLogOut(View v){

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("loginCheck");
        editor.commit();

        Intent intent = new Intent(MainActivity.this,StartActivity.class);
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

    //======================Lat lon 검색========================
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {


            Log.d("LOCATION", "onLocationChanged, location:" + location);
            lat = String.valueOf(location.getLongitude()); //경도
            lon = String.valueOf(location.getLatitude());   //위도
            //double altitude = location.getAltitude();   //고도
            //float accuracy = location.getAccuracy();    //정확도
        }
        public void onProviderDisabled(String provider) {
            // Disabled
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };
}
