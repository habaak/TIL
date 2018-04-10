package com.example.student.p246;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Resources res;

    ImageView imageView1, imageView2;
    Button bt_up,bt_dw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();
    }
    public void makeui(){
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        bt_up = findViewById(R.id.bt_up);
        bt_dw = findViewById(R.id.bt_dw);
    }
    public void clickbt(View v) {
        BitmapDrawable bitmap = null;
        if (v.getId() == R.id.bt_up) {
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.sv3);

            imageView1.setImageDrawable(bitmap);
        } else if (v.getId() == R.id.bt_dw) {
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.sv3);

            imageView2.setImageDrawable(bitmap);
        }
    }
}
