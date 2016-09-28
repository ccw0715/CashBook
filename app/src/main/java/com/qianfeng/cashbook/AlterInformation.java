package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AlterInformation extends AppCompatActivity {

    private String username;
    private int picId;
    private String sex;
    private String autograph;
    private ImageView iv;
    private AlertDialog dialog;
    private EditText et1;
    private EditText et2;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_information);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        picId = intent.getIntExtra("img", R.drawable.a1);
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setImageResource(picId);
        et1 = (EditText) findViewById(R.id.username);
        et2 = (EditText) findViewById(R.id.autograph);
        rg = ((RadioGroup) findViewById(R.id.sex));
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.boy:
                        sex="男";
                        break;
                    case R.id.girl:
                        sex="女";
                        break;
                    case R.id.other:
                        sex="其他";
                        break;
                }
            }
        });


    }

    public void change(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.mypiclayout, null);
        builder.setView(view1);
        RadioGroup rg = (RadioGroup) view1.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        picId=R.drawable.a1;
                        break;
                    case R.id.rb2:
                        picId=R.drawable.a2;
                        break;
                    case R.id.rb3:
                        picId=R.drawable.a3;
                        break;
                    case R.id.rb4:
                        picId=R.drawable.a4;
                        break;
                    case R.id.rb5:
                        picId=R.drawable.a5;
                        break;
                    case R.id.rb6:
                        picId=R.drawable.a6;
                        break;
                    case R.id.rb7:
                        picId=R.drawable.a7;
                        break;
                    case R.id.rb8:
                        picId=R.drawable.a8;
                        break;
                    case R.id.rb9:
                        picId=R.drawable.a9;
                        break;
                }
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    public void ensure(View view) {
        iv.setImageResource(picId);
        dialog.dismiss();
    }

    public void saved(View view) {
        username = et1.getText().toString();
        autograph = et2.getText().toString();
        final UserInformation userInformation = new UserInformation();
        userInformation.setName(username);
        userInformation.setPicId(picId);
        userInformation.setSex(sex);
        userInformation.setAutograph(autograph);
        userInformation.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Intent intent = new Intent(AlterInformation.this, MainActivity.class);
                    intent.putExtra("nickname",username);
                    intent.putExtra("picId",picId);
                    intent.putExtra("autograph",autograph);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}