package com.minson.learn;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        //活动加入在线活动列表
        ActivityCollector.addActivity(this);

        //将默认的标题栏隐藏
        //在res/values/styles.xml中没有修改DarkActionBar修改为NoActionBar的情况下可用
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null)
//        {
//            actionBar.hide();
//        }

        //获得从上一个活动传递的信息，然后点击seeButton显示此信息
        intent = getIntent();
        final String data = intent.getStringExtra("extra_data");

        Button seeButton = (Button) findViewById(R.id.seeButton);

        seeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, data.isEmpty() ? "Is NULL" : data, Toast.LENGTH_SHORT).show();
            }
        });

        //点击returnButton返回信息给上一个活动
        Button  returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.putExtra("data_return", "Hello mainActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //到第三个活动
        Button  goThird = (Button) findViewById(R.id.goThird);
        goThird.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("from_data", "secondActivity");
                startActivity(intent);
            }
        });

    }

    //返回上一个活动可以是按钮式返回，也可能式手机默认的返回键返回，所以需要增加这个处理
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("data_return", "Hello mainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

    //此活动销毁后，从ActivityCollector类中的activities中删除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "SecondActivity has killed!");
        ActivityCollector.removeActivity(this);
    }
}
