package com.minson.learn;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;
import org.litepal.util.Const;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //任何一个项目在AndroidManifest.xml中只能配置一个Application,其他的Application需要像下面这样初始化
        LitePalApplication.initialize(context);
    }

    public static Context getContext()
    {
        return context;
    }
}
