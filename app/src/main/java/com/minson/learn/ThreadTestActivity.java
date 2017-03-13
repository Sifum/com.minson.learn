package com.minson.learn;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

//异步信息处理（更新ui）
//Android异步信息处理主要由四个部分组成：Message, Handler, MessageQueue, Looper
public class ThreadTestActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int UPDATE_TEXT = 1;
    private TextView text;
    private ProgressDialog progressDialog = new ProgressDialog(ThreadTestActivity.this);

    //MessageQueue主要用来存放所有通过Handler发送的信息
    //Looper,每个线程只有一个Looper对象，用于管理MessageQueue,逐条取出MessageQueue中的信息，传递给handleMessage
    private Handler handler = new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    text.setText("Nice to meet you!");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_test_layout);

        text = (TextView) findViewById(R.id.showThreadText);
        Button changeText = (Button) findViewById(R.id.changeText);
        changeText.setOnClickListener(this);
        progressDialog.setTitle("AsyncTask is really convenient!");
        progressDialog.setMessage("Number from 100 to 0");
        progressDialog.setCancelable(false);
        new LoadTask().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.changeText:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Message是在线程之间传递信息，可以在内部携带少量信息，这里携带了what字段
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        //Handler的sendMessage方法最终会将信息传递到Handler的handleMessage方法中
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }


    class LoadTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                int start = 100;
                while(start > 0)
                {
                    publishProgress(start);
                    start --;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setMessage("Number is " + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.setCancelable(true);
        }
    }
}
