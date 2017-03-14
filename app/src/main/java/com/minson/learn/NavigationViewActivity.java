package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/*
 *完成此布局需要
 * navigation_view_layout.xml
 * nav_header.xml
 * nav_menu.xml
 * 需要的依赖是:
 *  compile 'com.android.support:design:23.2.1'
 *  compile 'de.hdodenhof:circleimageview:2.1.0'   //图片圆形化
 */
public class NavigationViewActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);//设置toolbar成为我们定义的标题栏
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        //虽然我们设置了自己的标题栏toolbar，但给新标题栏上设置有些属性还需要原来的ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.id.xxxx);
            // 设置HomeAsUp按钮图标，如果设置，将图片去ic_menu名称放置在drawable-xhdpi中
        }

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //设置初始的选中选项
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    //设置HomeAsUp按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //HomeAsUp的id永远是android.R.id.home
            case android.R.id.home:
                //openDrawer将滑动菜单展现出来,传入参数是保证和XML中定义一致。
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, NavigationViewActivity.class);
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
