package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 *异步信息处理（更新ui）
 *Android异步信息处理主要由四个部分组成：Message, Handler, MessageQueue, Looper
 * MessageQueue用于存放所有通过Handler的sendMessage发送的信息，形成等待处理的队列
 * Looper用于调取队列中的信息放进处理器处理
*/
public class ThreadTestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button changeText;
    private EditText showThreadText;
    private final int UPDATE_TEXT = 1;

    /*
     *处理者，所有在sendMessage中传递的信息都会到handleMessage中进行处理
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    showThreadText.setText("Nice to meet you");
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

        changeText = (Button) findViewById(R.id.changeText);
        showThreadText = (EditText) findViewById(R.id.showThreadText);

        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.changeText:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //构建信息，并使其携带what字段
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        //发送信息给处理器
                        handler.sendMessage(message);
                    }
                }).start();
        }
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, ThreadTestActivity.class);
        if (data.length > 0)
        {
            for(int i = 0; i < data.length; i++)
            {
                intent.putExtra("param" + i, data[i]);
            }
        }
        context.startActivity(intent);
    }
}
