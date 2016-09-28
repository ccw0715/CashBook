package com.qianfeng.cashbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Add_AccountBook extends AppCompatActivity {

    private User user;
    private Spinner year;
    private Spinner month;
    private Spinner day;

    private List<Integer> yearList;
    private List<Integer> monthList;
    private List<Integer> dayList;
    private int[] dayData = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private EditText title;
    private EditText content;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__acount_book);
        title = ((EditText) findViewById(R.id.title));
        content = ((EditText) findViewById(R.id.content));
        year = ((Spinner) findViewById(R.id.year));
        month = ((Spinner) findViewById(R.id.month));
        day = ((Spinner) findViewById(R.id.day));
        initYearData();
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, yearList);
        year.setAdapter(adapter1);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentYear = yearList.get(i);
                if (currentYear % 400 == 0 || (currentYear % 4 == 0 && currentYear % 100 != 0)) {
                    dayData[2] = 29;
                }
                initMonthData();
                ArrayAdapter adapter2 = new ArrayAdapter(Add_AccountBook.this, android.R.layout.simple_list_item_1, monthList);
                month.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentMonth = monthList.get(i);
                initDayData(currentMonth);
                ArrayAdapter adapter3 = new ArrayAdapter(Add_AccountBook.this, android.R.layout.simple_list_item_1, dayList);
                day.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentDay = dayList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    private void initDayData(int month) {
        dayList = new ArrayList<>();
        int count = dayData[month];
        for (int i = 1; i <= count; i++) {
            dayList.add(i);
        }
    }

    public void submit(View view) {
        String t = title.getText().toString();
        String s = content.getText().toString();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        if (t.equals("") || s.equals("")) {
            Toast.makeText(this, "内容为空！", Toast.LENGTH_SHORT).show();
        } else {
            AccountBook book = new AccountBook();
            book.setUsername(username);
            book.setYear(currentYear);
            book.setMonth(currentMonth);
            book.setDay(currentDay);
            book.setTitle(t);
            book.setContent(s);
            book.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        finish();
                    } else {
                        Toast.makeText(Add_AccountBook.this, "数据上传失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
