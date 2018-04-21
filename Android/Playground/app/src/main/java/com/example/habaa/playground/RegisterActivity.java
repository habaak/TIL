package com.example.habaa.playground;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etEmail,etNickName,etPwd,etAge;
    Button btnNext1,btnNext2,btnNext3,btnGoMain;
    TextView id;
    LinearLayout view1, view2, view3;
    RelativeLayout view4;
    RadioGroup rgGender;
    AsyncTask<String, Void, String> regsterRequest;
    String registerCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail=findViewById(R.id.email);
        etNickName=findViewById(R.id.nickName);
        etPwd=findViewById(R.id.pwd);
        etAge=findViewById(R.id.etAge);
        rgGender=findViewById(R.id.genderRG);


        btnNext1=findViewById(R.id.btnNext);
        btnNext2=findViewById(R.id.btnNext2);
        btnNext3=findViewById(R.id.btnNext3);
        btnGoMain=findViewById(R.id.btnGoMain);

        view1=findViewById(R.id.viewInputEmail);
        view2=findViewById(R.id.viewNamePwd);
        view3=findViewById(R.id.viewAgeGender);
        view4=findViewById(R.id.viewComplete);

        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);


    }
    public void clickNextBtn(View v){
            String email ="",name="",pwd="",age="",gender ="";
        if(v.getId()==R.id.btnNext){
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.btnNext2){
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);
            view4.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.btnNext3){


            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.btnGoMain){
            email = etEmail.getText().toString().trim();

            name = etNickName.getText().toString().trim();
            pwd = etPwd.getText().toString().trim();

            age = etAge.getText().toString().trim();
            int genderId;
            genderId = rgGender.getCheckedRadioButtonId();
            RadioButton rb= findViewById(genderId);
            gender = rb.getText().toString().trim();
            regsterRequest = new RegsterRequest().execute(StartActivity.serverUrl+"/register.do",email,pwd,name,age,gender);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public class RegsterRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            regiser(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5]);
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("RegisterCkeck -- "+registerCheck);
            if(registerCheck.equals("false")) {
                Toast.makeText(RegisterActivity.this,"회원 가입이 실패 했습니다.",Toast.LENGTH_LONG).show();
            } else if(registerCheck.equals("true")){
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
        JSONObject jsonDataObject =new JSONObject();
        JSONObject jsonObject = new JSONObject();
        public void regiser(String url, String email, String pwd, String name, String age, String gender){
            try {
                jsonDataObject.put("email",email);
                jsonDataObject.put("pwd",pwd);
                jsonDataObject.put("name",name);
                jsonDataObject.put("age",age);
                jsonDataObject.put("gender",gender);

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("pwd", pwd));
                nameValuePairs.add(new BasicNameValuePair("name",name));
                nameValuePairs.add(new BasicNameValuePair("age",age));
                nameValuePairs.add(new BasicNameValuePair("gender",gender));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE,"application/json"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                System.out.println("DATA -- "+email+"-"+pwd+"-"+name+"-"+age+"-"+gender);
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
                        String isSuccess = jsonObject.getString("registerSucess");
                        System.out.println(isSuccess+ " - REGISTER - "+isSuccess);
                        registerCheck=isSuccess;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
