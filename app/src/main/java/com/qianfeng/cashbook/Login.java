package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private String name;
    private String psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = ((EditText) findViewById(R.id.username));
        password = ((EditText) findViewById(R.id.password));
    }

    public void login(View view) {
        name = username.getText().toString();
        psw = password.getText().toString();
        if (name.equals("") && psw.equals("")) {
            Toast.makeText(Login.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //查询用户名
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("username", name);
            query.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e == null) {
                        if (list != null && list.size() > 0) {
                            //遍历list
                            for (User user : list) {
                                //判断密码是否正确
                                if (user.getPassword().equals(psw)) {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("username", name);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                                    password.setText("");
                                }
                            }
                        } else {
                            Toast.makeText(Login.this, "该用户还没注册", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                        }
                    }
                }
            });
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}
