package com.minson.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LearnSerializableActivity extends AppCompatActivity {
    private Person person;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_serializable_layout);

        Button serializeButton = (Button) findViewById(R.id.showSerialize);
        textView = (TextView) findViewById(R.id.showSerializeTexts);

        person = new Person();
        person.setName("Minson");
        person.setAge(29);
//就可以使用下面Intent进行活动之间的数据传递了
//        Intent intent = new Intent(LearnSerializableActivity.this, OtherACTY.class);
//        intent.putExtra("person_data", person);
//        startActivity(intent);

        //在下个对象中获取person的方法：
        // Person person = (Person) getIntent().getSerializableExtra("person_data");
    }
}
