package com.example.student.p221;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Resources res;
    Button bt1,bt2,bt3;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        makeui();
    }
    public void makeui(){
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        imageView = findViewById(R.id.imageView4);
    }
    public void clickBt(View v){
        BitmapDrawable bitmap=null;
        if(v.getId() == R.id.bt1){
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg5);
        }else if(v.getId() == R.id.bt2){
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg6);
        }else if(v.getId() == R.id.bt3){
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg7);
        }
        imageView.setImageDrawable(bitmap);
    }
}
