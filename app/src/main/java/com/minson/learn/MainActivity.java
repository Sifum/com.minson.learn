package com.minson.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        Button UIButton = (Button) findViewById(R.id.toLearnUI);
        UIButton.setOnClickListener(this);

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
        fileStoreButton.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.toLogin);
        loginButton.setOnClickListener(this);

        Button learndbButton = (Button) findViewById(R.id.toLearnDB);
        learndbButton.setOnClickListener(this);

        Button learnNotificationButton = (Button) findViewById(R.id.toLearnNotification);
        learnNotificationButton.setOnClickListener(this);

        Button webViewButton = (Button) findViewById(R.id.ToWebView);
        webViewButton.setOnClickListener(this);

        Button ThreadButton = (Button) findViewById(R.id.ToThreadTest);
        ThreadButton.setOnClickListener(this);

        Button asyncButton = (Button) findViewById(R.id.ToLearnAsync);
        asyncButton.setOnClickListener(this);

        Button otherButton = (Button) findViewById(R.id.ToOtherActivity);
        otherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ToLearnAsync:
                LearnAsyncTaskActivity.actionStart(MainActivity.this);
                break;
            case R.id.ToThreadTest:
                ThreadTestActivity.actionStart(MainActivity.this);
                break;
            case R.id.ToWebView:
                WebViewActivity.actionStart(MainActivity.this);
                break;
            case R.id.toLearnNotification:
                SendNotificationLayout.actionStart(MainActivity.this);
                break;
            case R.id.toLearnDB:
                DBLearnActivity.actionStart(MainActivity.this);
                break;
            case R.id.toLogin:
                LoginActivity.actionStart(MainActivity.this);
                break;
            case R.id.toFileStore:
                FileStoreActivity.actionStart(MainActivity.this);
                break;
            case R.id.toLearnUI:
                UIInterfaceActivity.actionStart(MainActivity.this);
                break;
            case R.id.ToOtherActivity:
                OtherActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
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
