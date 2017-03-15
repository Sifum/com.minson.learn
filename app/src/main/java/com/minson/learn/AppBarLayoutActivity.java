package com.minson.learn;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//在app_bar_layout.xml中增加了
/*
        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            app:layout_scrollFlags="scroll|enterAlways|snap"

            app:layout_behavior="@string/appbar_scrolling_view_behavior"

 */
//解决了卡片式布局的遮挡问题，而且更优化了。
public class AppBarLayoutActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Kiwi", R.drawable.kiwi),
            new Fruit("Lemon", R.drawable.lemon),
            new Fruit("Mango", R.drawable.mango),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Peach", R.drawable.peach),
            new Fruit("Pear", R.drawable.pear),
            new Fruit("Tomato", R.drawable.tomato),
    };

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_layout);

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
                Toast.makeText(AppBarLayoutActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // 开始实现卡片式布局
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    private void initFruits()
    {
        fruitList.clear();
        for(int i = 0; i < 50; i++)
        {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
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
        Intent intent = new Intent(context, AppBarLayoutActivity.class);
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
