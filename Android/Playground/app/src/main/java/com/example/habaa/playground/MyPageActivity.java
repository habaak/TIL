package com.example.habaa.playground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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


public class MyPageActivity extends AppCompatActivity implements AbsListView.OnScrollListener{
    private ListView listView;                      // 리스트뷰
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private List<Contents> list;                      // String 데이터를 담고있는 리스트
    private ContentsAdapter adapter;                // 리스트뷰의 아답터
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 20;                  // 한 페이지마다 로드할 데이터 갯수.
    private ProgressBar progressBar;                // 데이터 로딩중을 표시할 프로그레스바
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수


    TextView id_tv,post_tv,msg_tv,textView;
    ImageView pimageView,imageView;

    String[] cmtArr = new String[5];
    String[] imgurlArr = new String[5];
    String[] uidxArr = new String[5];
    String[] pidxArr = new String[5];
    String[] likeArr = new String[5];
    String[] dateArr = new String[5];

    String imageUrl =StartActivity.serverUrl+"/img/";

    String lon, lat,cmt,uidx,pidx,date,like;
    int pagenum;
    MainActivity.MainRequest mainRequest;
    MainActivity.UpdateMainRequest updateMainRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_tv=findViewById(R.id.textView);
        post_tv=findViewById(R.id.textView3);
        //msg_tv=findViewById(R.id.textView4);
        pimageView=findViewById(R.id.imageView);
        imageView=findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);
        id_tv.setText(LoginActivity.spname);
        textView.setText(LoginActivity.spname);

        mainRequest = (MainActivity.MainRequest) new MainRequest().execute(StartActivity.serverUrl+"/myView.do",lat,lon,String.valueOf(pagenum));
    }
    public void clickMainPage(View v){
        Intent intent = new Intent(MyPageActivity.this,MainActivity.class);
        startActivity(intent);
    }
    //사진올리기
    public void clickGoPostContent(View v){
        Intent intent = new Intent(MyPageActivity.this,PostContentsActivity.class);
        startActivity(intent);
    }
    //마이페이지
    public void clickMyPage(View v){
        Intent intent = new Intent(MyPageActivity.this,MyPageActivity.class);
        startActivity(intent);
    }
    //로그아웃
    public void clickLogOut(View v){

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("uidx");
        editor.remove("pwd");
        editor.remove("name");
        editor.remove("age");
        editor.remove("gender");
        editor.remove("loginChecker");
        editor.commit();

        Intent intent = new Intent(MyPageActivity.this,StartActivity.class);
        startActivity(intent);
    }
    //==============NetWork============
    public class MainRequest extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

            clearArr();
        }

        @Override
        protected String doInBackground(String... strings) {
            pagenum++;
            Log.d("PAGENUMBER!!!!!!!!!!", String.valueOf(pagenum));
            getMainJson(strings[0],strings[1],strings[2], String.valueOf(pagenum));
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            for(int i = 0; i < 5; i++){

                imgurlArr[i] += pidxArr[i]+".jpg";
                Log.d("ImgURL~~~~",imgurlArr[i]);
                Log.d("COMMENT~~~~",cmtArr[i]);
                String label = "Label " + ((page * OFFSET) + i);
                //getImage(imgurlArr[i]);
                list.add(new Contents(cmtArr[i],R.drawable.point,imgurlArr[i]));

            }
            clearArr();
        }



        private void getMainJson(String url,String lon, String lat, String pagenum){

            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lat", lat));
                nameValuePairs.add(new BasicNameValuePair("lon", lon));
                nameValuePairs.add(new BasicNameValuePair("pagenum",pagenum));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    Log.d("JSON",line);
                    System.out.println("Buffered rd -- "+line);

                    JSONArray jsonArray = new JSONArray(line);


                    System.out.println("JSONArray -- "+jsonArray);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String isSuccess = jsonObject.getString("MainSucess");
                        /*cmt = jsonObject.getString("cmt");
                        uidx = jsonObject.getString("uidx");
                        pidx = jsonObject.getString("pidx");
                        date = jsonObject.getString("dt");
                        like = jsonObject.getString("heart");*/
                        cmtArr[i] = jsonObject.getString("cmt");
                        uidxArr[i] = jsonObject.getString("uidx");
                        pidxArr[i] = jsonObject.getString("pidx");
                        dateArr[i] = jsonObject.getString("dt");
                        likeArr[i] = jsonObject.getString("heart");
                        //imgurlArr[i] = jsonObject.getString("img");

                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
            }

        }
    }
    //========================Update Networking========================
    public class UpdateMainRequest  extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            clearArr();
        }

        @Override
        protected String doInBackground(String... strings) {
            pagenum++;
            Log.d("PAGENUMBER!!!!!!!!!!", String.valueOf(pagenum));
            UpdateMainJson(strings[0],strings[1],strings[2], String.valueOf(pagenum));
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            for(int i = 0; i < 5; i++){

                imgurlArr[i]+= pidxArr[i]+".jpg";
                Log.d("ImgURL~~~~",imgurlArr[i]);
                Log.d("COMMENT~~~~",cmtArr[i]);
                String label = "Label " + ((page * OFFSET) + i);
                //getImage(imgurlArr[i]);
                list.add(new Contents(cmtArr[i],R.drawable.point,imgurlArr[i]));
            }
        }



        private void UpdateMainJson(String url,String lon, String lat, String pagenum){

            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lat", "299.999"));
                nameValuePairs.add(new BasicNameValuePair("lon", "299.999"));
                nameValuePairs.add(new BasicNameValuePair("pagenum",pagenum));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    Log.d("JSON",line);
                    System.out.println("Buffered rd -- "+line);

                    JSONArray jsonArray = new JSONArray(line);


                    System.out.println("JSONArray -- "+jsonArray);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String isSuccess = jsonObject.getString("MainSucess");
                        cmtArr[i] = jsonObject.getString("cmt");
                        uidxArr[i] = jsonObject.getString("uidx");
                        pidxArr[i] = jsonObject.getString("pidx");
                        dateArr[i] = jsonObject.getString("dt");
                        likeArr[i] = jsonObject.getString("heart");
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
            }

        }
    }
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

    //배열 CLEAR
    public void clearArr(){
        for (int i=0;i<5;i++){
            cmtArr[i] = "";
            imgurlArr[i] = imageUrl;
            uidxArr[i] = "";
            pidxArr[i] = "";
            likeArr[i] = "";
            dateArr[i]= "";
        }
    }
    //=========================Scroll=======================
    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
        // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
        // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
        // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            // 화면이 바닦에 닿을때 처리
            // 로딩중을 알리는 프로그레스바를 보인다.
            progressBar.setVisibility(View.VISIBLE);

            updateMainRequest = (MainActivity.UpdateMainRequest) new UpdateMainRequest().execute(StartActivity.serverUrl+"/myView.do",lat,lon,String.valueOf(pagenum));


            // 다음 데이터를 불러온다.
            getItem();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
        // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
        // totalItemCount : 리스트 전체의 총 갯수
        // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }

    private void getItem(){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // 다음 5개의 데이터를 불러와서 리스트에 저장한다.
        for(int i = 0; i < 5; i++){
            String label = "Label " + ((page * OFFSET) + i);
            list.add(new Contents(cmtArr[i],R.drawable.point,imgurlArr[i]));

        }

        // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                mLockListView = false;
            }
        },1000);
    }
}