package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {
    TextView ok_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        ok_id =findViewById(R.id.ok_id);
        Intent intent = getIntent();
        String id = intent.getStringExtra("loginid");
        ok_id.setText(id);
    }
}
