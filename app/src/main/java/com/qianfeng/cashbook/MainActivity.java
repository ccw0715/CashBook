package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity {

    private ImageView pic;
    private TextView content;
    private String username;
    private String nickname;
    private int picId;
    private String autograph;
    private TextView tv;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    content.setText(nickname);
                    tv.setText(autograph);
                    pic.setImageResource(picId);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        pic = ((ImageView) findViewById(R.id.pic));
        content = ((TextView) findViewById(R.id.content));
        tv = ((TextView) findViewById(R.id.autograph));
        initData();
        nickname = intent.getStringExtra("nickname");
        picId = intent.getIntExtra("picId", R.drawable.a1);
        autograph = intent.getStringExtra("autograph");
        content.setText(nickname);
        tv.setText(autograph);
        pic.setImageResource(picId);

    }

    private void initData() {

        BmobQuery<UserInformation> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<UserInformation>() {
            @Override
            public void done(List<UserInformation> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        //遍历list
                        for (UserInformation ui : list) {
                            nickname=ui.getName();
                            picId = ui.getPicId();
                            autograph=ui.getAutograph();
                        }
                    }
                }
            }
        });
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

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
                break;
            case R.id.quit:
                startActivity(new Intent(this, Login.class));
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }


}
