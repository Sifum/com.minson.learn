package com.minson.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple",
            "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple",
            "Strawberry", "Cherry", "Mango",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
