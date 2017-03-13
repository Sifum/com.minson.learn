package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//更好的启动活动的方法
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        Button returnSecond = (Button) findViewById(R.id.returnSecond);
        returnSecond.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ThirdActivity.actionStart(ThirdActivity.this, "data1", "data2");
            }
        });
    }

    //更好的启动活动的方法
    public static void actionStart(Context context, String datal, String data2)
    {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", datal);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
}
