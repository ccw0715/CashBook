package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class QueryData extends AppCompatActivity {

    private String username;
    private ListView lv;
    private List<AccountBook> listBook1;
    private AccountBook accountBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        lv = (ListView) findViewById(R.id.lv);
        listBook1=new ArrayList<>();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        initData1();
        for (AccountBook book : listBook1) {
            Log.d("哈哈日记", "onCreate: "+book.getYear());
            Log.d("哈哈日记", "onCreate: "+book.getTitle());
        }

    }

    public void lookMonth(View view) {
    }

    public void lookAll(View view) {

        MyAdapter adapter = new MyAdapter(QueryData.this, listBook1);
        lv.setAdapter(adapter);
    }

    private void initData1() {
        BmobQuery<AccountBook> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<AccountBook>() {
            @Override
            public void done(List<AccountBook> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0 && list != null) {
                        for (AccountBook book : list) {
                            accountBook = new AccountBook(book.getYear(),book.getMonth(),book.getDay(),
                                    book.getTitle(),book.getContent());
                            listBook1.add(accountBook);
                        }
                    }

                } else {
                    Toast.makeText(QueryData.this, "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
