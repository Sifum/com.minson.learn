package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LearnAsyncTaskActivity extends AppCompatActivity {

    private Button buttonNew;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_async_task_layout);

        buttonNew = (Button)findViewById(R.id.buttonNew);
        progressBar = (ProgressBar)findViewById(R.id.progressBar02);
        textView = (TextView)findViewById(R.id.textView01);

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadTask asyncTask = new LoadTask(textView, progressBar);
                asyncTask.execute(1000);
            }
        });
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, LearnAsyncTaskActivity.class);
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
