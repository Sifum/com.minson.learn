package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/*
 *CoordinatorLayout是一个加强版的FrameLayout，优点是：
 *悬浮按钮自动向上偏移Snackbar的同等高度，从而确保不会被遮挡，而当Snackbar消失的时候，悬浮按钮又会回到原来位置
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //FloatingActionButton的实现
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                 *Snackbar不同与Toast，它会当用户点击提示的时候执行一些额外的操作。
                 * 比如用户删除信息，就能又反悔的一次机会
                 * 传入的第一个参数是View，Snackbar自动会查找外层的布局
                 */
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(CoordinatorLayoutActivity.this, "data restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                Toast.makeText(CoordinatorLayoutActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, CoordinatorLayoutActivity.class);
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
