package com.example.student.p554;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    MyTask myTask;
    Button button;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);
        progressDialog = new ProgressDialog(MainActivity.this);
    }

    public void clickBt(View v){
        myTask = new MyTask("192.168.111.100");
        /*try {
            int result = myTask.execute().get(); //.get() : thread가 끝난 다음에 이후 문장 실행 ex) login
            Log.d("click",result+"@@@@@@@");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        myTask.execute();
        Log.d("click","@@@@@@@");
    }

    //thread                        parameter/ thread에서 onProgress에게 넘겨주는 값/return type
    class MyTask extends AsyncTask<String,Integer,Integer>{
        String msg;
        public MyTask(){}

        public MyTask(String msg){
            this.msg=msg;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(50);
            textView.setText("start Asyc");
            button.setEnabled(false);
            progressDialog.setTitle("Progress");
            progressDialog.setMessage("ing...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        //thread에서의 run 부분
        @Override
        protected Integer doInBackground(String... Strings) {
            int result=0;
            //String msg = Strings[0];
            Log.d("doInBackground",msg+"sleep zzZ");

            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result += i;
                publishProgress(result);//progress bar에 result값을 넣어줌
            }
            //textView.setText("End");//sub thread에서 main thread의 ui를 변경할 수 없다.
            Log.d("doInBackground",msg+"Awake!!!!");

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Integer result) {
            textView.setText("End Asyc : "+result);
            button.setEnabled(true);
            progressDialog.dismiss();
        }
    }
}
