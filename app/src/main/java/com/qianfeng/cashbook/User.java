package com.qianfeng.cashbook;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by 蔡灿武 on 2016/9/16.
 */
public class User extends BmobObject {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "用户：[" + "用户名：" + username + ",密码：" + password + "]";
    }
}
