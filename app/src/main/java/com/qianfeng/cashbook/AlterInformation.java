package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AlterInformation extends AppCompatActivity {

    private String username;
    private int picId;
    private int img[] = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5,
                R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9};
    private ImageView iv;
    private AlertDialog dialog;

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
        View view1 = LayoutInflater.from(this).inflate(R.layout.mypiclayout, null);
        RadioGroup rg = (RadioGroup) view1.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.a1:
                        picId=R.drawable.a1;
                        Toast.makeText(AlterInformation.this, "haha", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                }
            }
        });
        builder.setView(view1);
        dialog = builder.create();
        dialog.show();
        iv.setImageResource(picId);


    }

    public void save(View view) {
        
    }
}