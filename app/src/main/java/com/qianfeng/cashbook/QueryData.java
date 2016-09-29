package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class QueryData extends AppCompatActivity {

    private String username;
    private ListView lv;
    private List<AccountBook> listBook1;
    private List<AccountBook> listBook2;
    private AccountBook accountBook;
    private List<Integer> yearList;
    private List<Integer> monthList;
    private int year;
    private int month;

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyAdapter adapter1 = new MyAdapter(QueryData.this, listBook1);
                    lv.setAdapter(adapter1);
                    break;
                case 1:
                    MyAdapter adapter2 = new MyAdapter(QueryData.this, listBook2);
                    lv.setAdapter(adapter2);
                    break;
            }
        }
    };
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        lv = (ListView) findViewById(R.id.lv);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");


    }

    public void lookMonth(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.look_item, null);
        builder.setView(view1);
        Spinner sp1 = (Spinner) view1.findViewById(R.id.spinner1);
        Spinner sp2 = (Spinner) view1.findViewById(R.id.spinner2);
        initYearData();
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, yearList);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = yearList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initMonthData();
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthList);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = monthList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void btn(View view) {
        listBook2 = new ArrayList<>();
        BmobQuery<AccountBook> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<AccountBook>() {
            @Override
            public void done(List<AccountBook> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0 && list != null) {
                        for (AccountBook book : list) {
                            if(book.getYear()==year && book.getMonth()== month){
                                accountBook = new AccountBook(book.getYear(), book.getMonth(), book.getDay(),
                                        book.getTitle(), book.getContent());
                                listBook2.add(accountBook);
                            }
                        }
                        if(listBook2.isEmpty()){
                           Toast.makeText(QueryData.this, "没有"+year+"年"+month+"月的数据！", Toast.LENGTH_SHORT).show();
                        }
                        Collections.sort(listBook2);
                    }else {
                        Toast.makeText(QueryData.this, "该用户没有添加数据！", Toast.LENGTH_SHORT).show();
                    }
                    mHandle.sendEmptyMessage(1);
                } else {
                    Toast.makeText(QueryData.this, "网络错误", Toast.LENGTH_SHORT).show();
                    mHandle.sendEmptyMessage(2);
                }
            }
        });
        dialog.dismiss();
    }

    public void lookAll(View view) {
        initData();

    }

    private void initYearData() {
        yearList = new ArrayList<>();
        for (int i = 1900; i < 2017; i++) {
            yearList.add(i);
        }
    }

    private void initMonthData() {
        monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
    }

    private void initData() {
        listBook1 = new ArrayList<>();
        BmobQuery<AccountBook> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<AccountBook>() {
            @Override
            public void done(List<AccountBook> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0 && list != null) {
                        for (AccountBook book : list) {
                            accountBook = new AccountBook(book.getYear(), book.getMonth(), book.getDay(),
                                    book.getTitle(), book.getContent());
                            listBook1.add(accountBook);
                        }
                        Collections.sort(listBook1);
                    }else {
                        Toast.makeText(QueryData.this, "该用户没有添加数据！", Toast.LENGTH_SHORT).show();
                    }
                    mHandle.sendEmptyMessage(0);
                } else {
                    Toast.makeText(QueryData.this, "网络错误", Toast.LENGTH_SHORT).show();
                    mHandle.sendEmptyMessage(2);
                }
            }
        });
    }


}
