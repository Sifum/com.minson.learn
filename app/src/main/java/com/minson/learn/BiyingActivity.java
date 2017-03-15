package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//利用必应的每日一图，设置为背景

/**
 * 依赖：
 * compile 'com.squareup.okhttp3:okhttp:3.4.1'
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 *
 */
public class BiyingActivity extends AppCompatActivity {

    private ImageView bingPicImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biying_background_layout);

        bingPicImage = (ImageView) findViewById(R.id.bing_pic_img);
        //利用SharedPreferences来保存键值对数据
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImage);
        } else {
            loadBingPic();
        }
    }

    private void loadBingPic()
    {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        new HttpUtil().sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                Log.d("BiyingActivity", bingPic);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(BiyingActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(BiyingActivity.this).load(bingPic).into(bingPicImage);
                    }
                });

            }
        });
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, BiyingActivity.class);
        if (data.length > 0)
        {
            for(int i = 0; i < data.length; i++)
            {
                intent.putExtra("param" + i, data[i]);
            }
        }
        context.startActivity(intent);
    }

    class HttpUtil {
        public void sendOkHttpRequest(String address, okhttp3.Callback callback)
        {
            //构建OkHttpClient的实例
            OkHttpClient client = new OkHttpClient();
            //发起请求
            Request request = new Request.Builder().url(address).build();
            //client.newCall(request)返回的就是Response对象，enqueue将用回调callback处理请求回应。
            client.newCall(request).enqueue(callback);
        }
    }

}
