package com.minson.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ActivityCollector.addActivity(this);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String data = "hello Second!";
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivityForResult(intent, 1);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LearnUIActivity.class);
                startActivity(intent);
            }
        });

        Button percentButton = (Button) findViewById(R.id.percentButton);
        percentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PercentFrameActivity.class);
                startActivity(intent);
            }
        });

        Button listViewButton = (Button) findViewById(R.id.toListView);
        listViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        Button fileStoreButton = (Button) findViewById(R.id.toFileStore);
        fileStoreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FileStoreActivity.class);
                startActivity(intent);
            }
        });

        Button loginButton = (Button) findViewById(R.id.toLogin);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button learndbButton = (Button) findViewById(R.id.toLearnDB);
        learndbButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DBLearnActivity.class);
                startActivity(intent);
            }
        });

        Button learnNotificationButton = (Button) findViewById(R.id.toLearnNotification);
        learnNotificationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendNotificationLayout.class);
                startActivity(intent);
            }
        });

        Button webViewButton = (Button) findViewById(R.id.ToWebView);
        webViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });


    }

    //从打开的活动中返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK)
                {
                    String returnedData = data.getStringExtra("data_return");
                    //打印了返回的数据，第一参数式当前的类名， 第二个参数式字符串
                    Log.d("MainActivity", returnedData);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            //如果点击closeApp,那么会将所有活动finish掉
            case R.id.closeApp:
                ActivityCollector.finishAll();
                break;
            default:
        }
        return true;
    }

}
