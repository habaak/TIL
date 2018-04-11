package com.example.student.p253;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    LayoutInflater inflater;
    TextView stv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();
    }
    public void makeui(){
        container = findViewById(R.id.container);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        stv = findViewById(R.id.textView);
    }
    public void clickBt(View v){
        final View v1 = inflater.inflate(R.layout.sub,container,true);
        TextView st = container.findViewById(R.id.stv);


        stv.setText("ButtonClick");
        Button sbt1 = container.findViewById(R.id.sbt1);
        Button sbt2 = container.findViewById(R.id.sbt2);
        sbt1.setText("Sub Button 1");
        sbt2.setText("Sub Button 2");

        sbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sub2변경
                Toast.makeText(MainActivity.this, "sub2",);
                inflater.inflate(R.layout.sub2,container,true);

                TextView st2 = container.findViewById(R.id.textView2);
                st2.setText("Welcome to SUB2");
            }
        });
        sbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sub3변경
                View v3 =inflater.inflate(R.layout.sub3,container,true);
                v1.setVisibility(view.INVISIBLE);
                v3.setVisibility(View.VISIBLE);
                TextView st3 = container.findViewById(R.id.textView3);
                st3.setText("Welcome to SUB3");
            }
        });
    }
}
