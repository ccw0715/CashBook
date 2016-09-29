package com.qianfeng.cashbook;

import cn.bmob.v3.BmobObject;

/**
 * Created by 蔡灿武 on 2016/9/26 0026.
 */

public class AccountBook extends BmobObject implements Comparable<AccountBook>{
    private String username;
    private int year;
    private int month;
    private int day;
    private String  title;
    private String content;

    public String getUsername() {
        return username;
    }

    public AccountBook() {
    }

    public AccountBook(int year, int month, int day, String title, String content) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(AccountBook o) {
        int i=o.getYear()-this.getYear();
        if(i==0){
            i=o.getMonth()-this.getMonth();
            if(i==0){
                i=o.getDay()-this.getDay();
            }
        }
        return i;
    }
}
