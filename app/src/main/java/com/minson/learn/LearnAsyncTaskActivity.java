package com.minson.learn;

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

}
