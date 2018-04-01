package com.example.habaa.studyapp01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class WriteActivity extends AppCompatActivity {

    EditText edit;
    Button save_button;
    String contents;
    String dirPath = MainActivity.dirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        edit = (EditText) findViewById(R.id.text);
        File file = new File(dirPath);
    }

    public void onClickedSave(View v) {
        AlertDialog.Builder save_confirm = new AlertDialog.Builder(WriteActivity.this);

        final EditText et_save_name = new EditText(WriteActivity.this);
        save_confirm.setView(et_save_name);



        save_confirm.setMessage("저장할꺼야? 파일 이름 입력해줘").setCancelable(false).setPositiveButton("저장",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         // 입력 받은 값 String으로 저장
                        contents = edit.getText().toString();
                        String save_name = et_save_name.getText().toString();
                        Toast.makeText(WriteActivity.this, contents, Toast.LENGTH_LONG).show();
                        Toast.makeText(WriteActivity.this, save_name, Toast.LENGTH_LONG).show();
                        //File Write
                        try {
                            writeTextFile(save_name, contents);
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

    public void writeTextFile(String filename, String contents){
        try{
            FileOutputStream fos = new FileOutputStream(dirPath+"/"+filename, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(contents);
            bw.flush();

            bw.close();
            fos.close();
        }catch (Exception e){
            Log.v("writeTextFile ERROR", e.toString());
        }

    }
}
