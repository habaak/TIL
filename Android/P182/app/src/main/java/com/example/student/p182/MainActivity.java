package com.example.student.p182;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    TextView tv;

    final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1= (Button) findViewById(R.id.btA);
        bt2= (Button)findViewById(R.id.btB);
        bt3= (Button)findViewById(R.id.btC);
        bt4= (Button)findViewById(R.id.btD);
        bt5= (Button)findViewById(R.id.btE);
        bt6= (Button)findViewById(R.id.btF);
        tv = (TextView)findViewById(R.id.showtext);
        
    }
    public void onClickA(View v){
        //Intent intent = new Intent(this,getText(R.id.btA));
        tv.setText("A");
    }
    public void onClickB(View v){
        tv.setText("B");
    }
    public void onClickC(View v){
        tv.setText("C");
    }
    public void onClickD(View v){
        tv.setText("D");
    }
    public void onClickE(View v){
        tv.setText("E");
    }
    public void onClickF(View v){
        tv.setText("F");
    }
}
