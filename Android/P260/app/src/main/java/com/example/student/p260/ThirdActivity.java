package com.example.student.p260;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    int num1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        num1 = intent.getIntExtra("num1",0);
        int result = num1 * 2000;
        //Toast.makeText(ThirdActivity.this,num1,Toast.LENGTH_LONG).show();
        Intent intent2 = new Intent();
        intent2.putExtra("result",result);

        setResult(RESULT_OK,intent2);

        finish();
    }
}
