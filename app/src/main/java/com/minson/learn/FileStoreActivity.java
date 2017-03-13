package com.minson.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//使用文件来存储数据
public class FileStoreActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private FileOutputStream out = null;
    private BufferedWriter write = null;
    private FileInputStream in = null;
    private BufferedReader reader = null;
    private StringBuilder content = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_store_layout);

        editText = (EditText) findViewById(R.id.inputText);
        Button storeData = (Button) findViewById(R.id.storeData);
        Button showData = (Button) findViewById(R.id.showData);

        storeData.setOnClickListener(this);
        showData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //保存数据
            case R.id.storeData:
                try {
                    out = openFileOutput("data", MODE_PRIVATE);
                    write = new BufferedWriter(new OutputStreamWriter(out));
                    write.write(editText.getText().toString());
                    Log.d("FileStoreActivity", "data store success!");
                    Toast.makeText(FileStoreActivity.this, "data store success", Toast.LENGTH_SHORT).show();
                    editText.setText("data store success!");
                } catch (IOException E)
                {
                    E.printStackTrace();
                } finally {
                    try {
                        if (write != null)
                        {
                            write.close();
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }

                break;
            //读取数据
            case R.id.showData:
                try
                {
                    in = openFileInput("data");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    while ((line = reader.readLine()) != null)
                    {
                        content.append(line);
                    }
                    Log.d("FileStoreActivity", content.toString());
                    Toast.makeText(FileStoreActivity.this, content.toString(), Toast.LENGTH_SHORT).show();
                    editText.setText(content.toString());
                } catch (IOException E)
                {
                    E.printStackTrace();
                } finally {
                    try {
                        if (reader != null)
                        {
                            reader.close();
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }
}
