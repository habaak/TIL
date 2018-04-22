package com.example.habaa.playground;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReplyActivity extends AppCompatActivity {
    TextView tvIdAndComment,tvTime;

    ListView lvReply;
    ReplyActivity replyActivity;
    ArrayList<reply> replyArrayList;

    EditText etReply;
    Button btnPostReply;

    ReplyRequest replyRequest;
    PostReply postReply;
    String registerReplySucess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        tvIdAndComment = findViewById(R.id.tvIdAndComment);
        tvTime = findViewById(R.id.tvTime);
        etReply = findViewById(R.id.etReply);
        btnPostReply = findViewById(R.id.btnPostPic);
        lvReply = (ListView) findViewById(R.id.lvReply);

        replyArrayList = new ArrayList<reply>();

        //replyRequest = (ReplyRequest) new ReplyRequest.execute(StartActivity.serverUrl+"/login.do");
    }
    public void clickPostReply(View v){
        String reply = etReply.getText().toString();
        //postReply = (PostReply) new PostReply.execute(StartActivity.serverUrl+"/login.do",uidx,pidx,reply);

    }
    //==============리플 블러오기===========
    public class ReplyRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            showReply(strings[0],strings[1],strings[2],strings[3]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }
        JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        private  void showReply(String url,String pidx,String uidx, String reply){
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("pidx",pidx));
                nameValuePairs.add(new BasicNameValuePair("uidx",uidx));
                nameValuePairs.add(new BasicNameValuePair("reply",reply));

                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //========================================
                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println("Buffered rd -- "+line);

                    JSONArray jsonArray = new JSONArray(line);


                    System.out.println("JSONArray -- "+jsonArray);

                    /*for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String isSuccess = jsonObject.getString("registerReplySucess");
                        System.out.println(isSuccess+ " - Login - "+isSuccess);
                        registerReplySucess=isSuccess;
                    }*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //=============리플 등록=================
    public class PostReply extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            postReply(strings[0],strings[1],strings[2],strings[3]);
            return null;
        }


        JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        private  void postReply(String url,String uidx,String pidx, String reply) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("pidx", pidx));
                nameValuePairs.add(new BasicNameValuePair("uidx", uidx));
                nameValuePairs.add(new BasicNameValuePair("reply", reply));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //========================================
                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println("Buffered rd -- "+line);

                    JSONArray jsonArray = new JSONArray(line);


                    System.out.println("JSONArray -- "+jsonArray);

                    /*for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String isSuccess = jsonObject.getString("registerReplySucess");
                        System.out.println(isSuccess+ " - Login - "+isSuccess);
                        registerReplySucess=isSuccess;
                    }*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
