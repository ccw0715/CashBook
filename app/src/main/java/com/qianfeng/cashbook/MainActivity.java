package com.qianfeng.cashbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView pic;
    private TextView content;
    private int img[]=new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,
            R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = ((ImageView) findViewById(R.id.pic));
        content = ((TextView) findViewById(R.id.content));
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        content.setText(username);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i:img){
                    pic.setImageResource(img[i]);

                }
            }
        });
    }
    public void alter(View view) {

    }
    public void addNote(View view) {
    }

    public void look(View view) {
    }
    //创建菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取菜单管理器
        MenuInflater inflater = getMenuInflater();
        //加载菜单文件
        inflater.inflate(R.menu.menu, menu);

        //返回true表示菜单创建成功
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                break;
            case R.id.quit:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
