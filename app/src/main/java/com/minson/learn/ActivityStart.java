package com.minson.learn;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/3/12 0012.
 * 有个闪频，需要解决
 */
public class ActivityStart {
    private Context ct;
    private Intent intent;

    public ActivityStart(Context context) {
        ct = context;
    }

    public void actionStart(String context, String ... args)
    {
        intent = new Intent(ct, context.getClass());
        if (args.length > 0)
        {
            for(int i = 0; i  < args.length; i++)
            {
                intent.putExtra("param" + i, args[i]);
            }
        }
        ct.startActivity(intent);
    }
}
