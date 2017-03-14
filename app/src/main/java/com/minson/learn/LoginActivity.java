package com.minson.learn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/*
 * 此保存密码方法仅用于学习SharedPreferences键值对存储方法
 */
public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private CheckBox remenberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //实例化SharedPreferences对象。
        // getDefaultSharedPreferences接收Context参数，并自动使用当前程序的包名作为前缀来命名SharedPreferences文件
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.submit);
        remenberPass = (CheckBox) findViewById(R.id.remember_pass);

        //查看是否应有保存的数据，有的话填入账户和密码框
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember)
        {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remenberPass.setChecked(true);
        }

        //Submit按钮进行提交
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //如果用户和密码正确
                if(account.equals("admin") && password.equals("123456"))
                {
                    //获取SharedPreferences.Editor读对象
                    editor = pref.edit();
                    //如果记住密码
                    if(remenberPass.isChecked())
                    {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    //可以利用Intent进行页面跳转
                    Toast.makeText(LoginActivity.this, "login success!", Toast.LENGTH_SHORT).show();
                }
                //如果错误
                else
                {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void actionStart(Context context, String ... data)
    {
        Intent intent = new Intent(context, LoginActivity.class);
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
