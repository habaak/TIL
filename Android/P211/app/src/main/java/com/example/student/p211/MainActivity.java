package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tx_id, tx_pwd;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeUi();
    }
    public void makeUi(){
        tx_id = findViewById(R.id.tx_id);
        tx_pwd = findViewById(R.id.tx_pwd);
        bt_login = findViewById(R.id.bt_login);

    }
    public void login(View v){
        String id = tx_id.getText().toString();
        String pwd = tx_pwd.getText().toString();

        tx_id.setText("");
        tx_pwd.setText("");
        Toast.makeText(this,id+" "+pwd,Toast.LENGTH_LONG).show();
        Intent intent = null;
        if(id.equals("qq")&&pwd.equals("11")){
            Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            intent.putExtra("loginid",id);
            intent = new Intent(this,LoginSuccessActivity.class);

        }else{
            Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();

            intent = new Intent(this,LoginSuccessActivity.class);
        }
        startActivity(intent);
    }
}
