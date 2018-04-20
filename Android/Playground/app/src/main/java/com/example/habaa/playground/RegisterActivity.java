package com.example.habaa.playground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail,etNickName,etPwd,etAge;
    Button btnNext1,btnNext2,btnNext3,btnGoMain;
    TextView id;
    Spinner genderSpinner;
    LinearLayout view1, view2, view3;
    RelativeLayout view4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail=findViewById(R.id.email);
        etNickName=findViewById(R.id.nickName);
        etPwd=findViewById(R.id.pwd);
        etAge=findViewById(R.id.etAge);
        genderSpinner=findViewById(R.id.genderSpinner);

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

        }
    }
}
