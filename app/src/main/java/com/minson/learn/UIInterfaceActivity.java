package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UIInterfaceActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiinterface_layout);

        Button ordinaryUIButton = (Button) findViewById(R.id.toLearnOrdinaryUI);
        Button materialButton = (Button) findViewById(R.id.toLearnMaterialUI);
        ordinaryUIButton.setOnClickListener(this);
        materialButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.toLearnOrdinaryUI:
                LearnUIActivity.actionStart(UIInterfaceActivity.this);
                break;
            case R.id.toLearnMaterialUI:
                MaterialDesignActivity.actionStart(UIInterfaceActivity.this);
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, UIInterfaceActivity.class);
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
