package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_layout);

        Button toolbarButton = (Button) findViewById(R.id.toToolbar);

        toolbarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaterialDesignActivity.this, ToolbarActivity.class);
                startActivity(intent);
            }
        });

        Button drawerButton = (Button) findViewById(R.id.toDrawerLayout);
        drawerButton.setOnClickListener(this);

        Button navigationButton = (Button) findViewById(R.id.toNavigationView);
        navigationButton.setOnClickListener(this);

        Button floatingButton = (Button) findViewById(R.id.toFloatingAction);
        floatingButton.setOnClickListener(this);

        Button coordinatorLayoutButton = (Button) findViewById(R.id.toCoordinatorLayout);
        coordinatorLayoutButton.setOnClickListener(this);

        Button cardViewButton = (Button) findViewById(R.id.toCardView);
        cardViewButton.setOnClickListener(this);

        Button appBarButton = (Button) findViewById(R.id.toAppBarLayout);
        appBarButton.setOnClickListener(this);

        Button swipeFreshButton = (Button) findViewById(R.id.toSwipeFresh);
        swipeFreshButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.toDrawerLayout:
                DrawerLayoutActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toNavigationView:
                NavigationViewActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toFloatingAction:
                FloatingActionActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toCoordinatorLayout:
                CoordinatorLayoutActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toCardView:
                CardViewActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toAppBarLayout:
                AppBarLayoutActivity.actionStart(MaterialDesignActivity.this);
                break;
            case R.id.toSwipeFresh:
                SwipeFreshActivity.actionStart(MaterialDesignActivity.this);
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, MaterialDesignActivity.class);
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
