package com.minson.learn;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class SendNotificationLayout extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_notification_layout);

        Button sendButton = (Button) findViewById(R.id.sendNotificationButton);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sendNotificationButton:
                //gou
                Intent[] intents = {new Intent(SendNotificationLayout.this, GetNotificationLayout.class)};
                //参数说明：context, 0, Intent对象数组，用于确定PendingIntent的行为，有FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT,FLAG_UPDATE_CURRENT
                PendingIntent pi = PendingIntent.getActivities(SendNotificationLayout.this, 0, intents, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //简化：Notification notification = new NotificationCompat.Builder(context).build();
                Notification notification = new NotificationCompat.Builder(SendNotificationLayout.this)
                        .setContentTitle("This is content title") //通知标题
                        .setContentText("This is context text") //通知正文
                        .setWhen(System.currentTimeMillis()) //通知时间
                        .setSmallIcon(R.mipmap.ic_launcher) //通知小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)) //通知大图标
                        .setContentIntent(pi) //传入点击后的行为意图
                        .setAutoCancel(true) //是否点击后消失
                        //.setSound(Uri.fromFile(new File("/System/media/audio/ringtones/luna.ogg"))) 播放声音
                        //.setVibrate(new long[]{0, 1000, 1000, 1000}) 震动
                        //.setLights(Color.GREEN, 1000, 1000)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND) //NotificationCompat下有很多默认的设置可用
                        //.setPriority(NotificationCompat.PRIORITY_HIGH)
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_img)))
                        .build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }


    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, SendNotificationLayout.class);
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
