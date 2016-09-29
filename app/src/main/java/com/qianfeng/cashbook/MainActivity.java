package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView pic;
    private TextView content;
    private String username;
    private String nickname;
    private int picId;
    private String autograph;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        pic = ((ImageView) findViewById(R.id.pic));
        content = ((TextView) findViewById(R.id.content));
        tv = ((TextView) findViewById(R.id.autograph));
        nickname = intent.getStringExtra("nickname");
        picId = intent.getIntExtra("picId", R.drawable.a1);
        autograph = intent.getStringExtra("autograph");
        content.setText(nickname);
        tv.setText(autograph);
        pic.setImageResource(picId);
    }

    //添加记账本
    public void addNote(View view) {
        Intent intent = new Intent(this, Add_AccountBook.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void look(View view) {
        Intent intent = new Intent(this, QueryData.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


    public void more(View view) {
        Toast.makeText(this, "更多功能敬请期待！", Toast.LENGTH_SHORT).show();
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
        switch (item.getItemId()) {
            case R.id.setting:
                break;
            case R.id.alterInformation:
                Intent intent = new Intent(this, AlterInformation.class);
                intent.putExtra("username", username);
                intent.putExtra("nickname",nickname);
                intent.putExtra("autograph",autograph);
                intent.putExtra("img", picId);
                startActivity(intent);
                finish();
                break;
            case R.id.quit:
                startActivity(new Intent(this, Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
