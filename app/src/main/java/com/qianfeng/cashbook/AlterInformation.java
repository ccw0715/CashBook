package com.qianfeng.cashbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AlterInformation extends AppCompatActivity {

    private String username;
    private int picId;
    private int img[] = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5,
                R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9};
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_information);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        picId = intent.getIntExtra("img", R.drawable.a1);
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setImageResource(picId);


    }

    public void change(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView()

    }
}