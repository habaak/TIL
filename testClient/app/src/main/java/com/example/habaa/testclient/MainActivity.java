package com.example.habaa.testclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Client client;
    private EditText edtTxt;
    private Button btnSend;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxt = findViewById(R.id.etSpeed);
        btnSend = findViewById(R.id.btnSend);
        client = new Client();
        client.start();
    }

    public void onClickBtn(View v) {
        if(edtTxt.getText() != null && !edtTxt.getText().toString().equals("")) {
            message = edtTxt.getText().toString();
            client.sendMessage(message);
        }
    }
}
