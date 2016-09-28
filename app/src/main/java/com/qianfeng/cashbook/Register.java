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
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText password2;
    private String name = "";
    private String pwd = "";
    private String pwd2 = "";

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = ((EditText) findViewById(R.id.username));
        password = ((EditText) findViewById(R.id.password));
        password2 = ((EditText) findViewById(R.id.password2));
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    name = username.getText().toString();
                    BmobQuery<User> query = new BmobQuery<>();
                    query.addWhereEqualTo("username", name);
                    query.findObjects(new FindListener<User>() {
                        @Override
                        public void done(List<User> list, BmobException e) {
                            if (e == null) {
                                if (list != null && list.size() > 0) {
                                    Toast.makeText(Register.this, "该用户已经被注册了！", Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                } else {

                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public void ensure(View view) {

        pwd = password.getText().toString();
        pwd2 = password2.getText().toString();
        if (!name.equals("") && !pwd.equals("") && !pwd2.equals("")) {
            if (pwd.equals(pwd2)) {
                user.setUsername(name);
                user.setPassword(pwd);
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            intent.putExtra("username",name);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "添加数据失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "请重新输入密码！", Toast.LENGTH_SHORT).show();
                password2.setText("");
            }
        } else {
            Toast.makeText(this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear(View view) {
        username.setText("");
        password.setText("");
        password2.setText("");
    }
}
