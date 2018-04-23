package com.example.habaa.playground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MyPageActivity extends AppCompatActivity {

    TextView id_tv,post_tv,msg_tv;
    ImageView pimageView,imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_tv=findViewById(R.id.textView);
        post_tv=findViewById(R.id.textView3);
        msg_tv=findViewById(R.id.textView4);
        pimageView=findViewById(R.id.imageView);
        imageView=findViewById(R.id.imageView2);

    }
    public void clickbt(View v){
        if(v.getId()==R.id.mod_bt){

        }else if(v.getId()==R.id.set_bt){

        }
    }
}