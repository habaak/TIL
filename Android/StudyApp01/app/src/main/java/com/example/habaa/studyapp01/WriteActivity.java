package com.example.habaa.studyapp01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;


public class WriteActivity extends AppCompatActivity {

    EditText edit;
    Button save_button;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        edit = (EditText) findViewById(R.id.text);

    }

    public void onClickedSave(View v) {
        AlertDialog.Builder save_confirm = new AlertDialog.Builder(WriteActivity.this);

        final EditText save_name = new EditText(WriteActivity.this);
        save_confirm.setView(save_name);



        save_confirm.setMessage("저장할꺼야? 파일 이름 입력해줘").setCancelable(false).setPositiveButton("저장",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         // 입력 받은 값 String으로 저장
                        text = edit.getText().toString();
                        Toast.makeText(WriteActivity.this, text, Toast.LENGTH_LONG).show();

                        //File Write
                        try {

                            FileOutputStream outstream = openFileOutput(save_name + ".txt", WriteActivity.MODE_WORLD_WRITEABLE);
                            outstream.write(text.getBytes());
                            outstream.close();
                            Toast.makeText(WriteActivity.this, "저장 했다~", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(WriteActivity.this, "저장 못했다 ㅜㅜ", Toast.LENGTH_LONG).show();
                        }

                    }
                }).setNegativeButton("안해",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //돌아가기
                    }
                });
        AlertDialog alert = save_confirm.create();
        alert.show();
    }
}
