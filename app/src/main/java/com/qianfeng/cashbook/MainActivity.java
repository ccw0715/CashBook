package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private int img = R.drawable.a1;
    private int picId;
    private String autograph;
    private TextView tv;

    private boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isFirst){
            Toast.makeText(this, "请先完善个人资料", Toast.LENGTH_SHORT).show();
            isFirst=false;
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
        }else {
            pic = ((ImageView) findViewById(R.id.pic));
            content = ((TextView) findViewById(R.id.content));
            tv = ((TextView) findViewById(R.id.autograph));
            Intent intent = getIntent();
            nickname = intent.getStringExtra("nickname");
            picId = intent.getIntExtra("picId", img);
            autograph = intent.getStringExtra("autograph");
            content.setText(nickname);
            tv.setText(autograph);
            pic.setImageResource(picId);
        }
        Log.d("哈哈日记", "onCreate: ");
        Log.d("哈哈日记", "onCreate: "+isFirst);
        Log.d("哈哈日记", "onCreate: "+username);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("哈哈日记", "onPause: ");
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
                intent.putExtra("img", img);
                startActivity(intent);
                break;
            case R.id.quit:
                startActivity(new Intent(this, Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
