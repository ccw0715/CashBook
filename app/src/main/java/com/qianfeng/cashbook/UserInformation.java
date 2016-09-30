package com.qianfeng.cashbook;

import cn.bmob.v3.BmobObject;

/**
 * Created by 蔡灿武 on 2016/9/25.
 */

public class UserInformation extends BmobObject {
    private int picId;
    private String name;
    private String nickname;
    private String sex;
    private String autograph;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }
}
