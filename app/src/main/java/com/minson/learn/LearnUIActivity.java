package com.minson.learn;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LearnUIActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_ui_layout);

        //EditText的用法
        editText = (EditText) findViewById(R.id.edit_text);
        //showTexts按钮用于显示文本框输入的内容
        Button showTexts = (Button) findViewById(R.id.showTexts);
        showTexts.setOnClickListener(this);


        //ImageView的动态显示用法
        Button showImg = (Button) findViewById(R.id.imgShow);
        imageView = (ImageView) findViewById(R.id.imgToShow);
        showImg.setOnClickListener(this);

        //ProgressBar的使用
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Button progressButton = (Button) findViewById(R.id.progressButton);
        progressButton.setOnClickListener(this);

        //AlertDialog的使用
        Button showAlert = (Button) findViewById(R.id.showAlert);
        showAlert.setOnClickListener(this);

        //ProgressDialog的使用
        Button showProgressDialog = (Button) findViewById(R.id.showProgressDialog);
        showProgressDialog.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.showTexts:
                String inputText = editText.getText().toString();
                if(!inputText.isEmpty())
                    Toast.makeText(LearnUIActivity.this, inputText, Toast.LENGTH_SHORT).show();
                break;
            //动态加载img的方法
            case R.id.imgShow:
                imageView.setImageResource(R.drawable.img_1);
                break;
            //进度条的加载
            case R.id.progressButton:
                if(progressBar.getVisibility() == View.GONE)
                {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            //弹窗的使用
            case R.id.showAlert:
                AlertDialog.Builder dialog = new AlertDialog.Builder(LearnUIActivity.this);
                dialog.setTitle("This is a dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false); //是否有取消按钮
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("LearnUIActivity", "You click ok");
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("LearnUIActivity", "You click Cancel");
                    }
                });
                dialog.show();
                break;
            //ProgressDialog的显示
            case R.id.showProgressDialog:
                ProgressDialog progressDialog = new ProgressDialog(LearnUIActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
