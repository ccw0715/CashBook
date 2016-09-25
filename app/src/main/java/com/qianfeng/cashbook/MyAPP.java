package com.qianfeng.cashbook;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by 蔡灿武 on 2016/9/25.
 */

public class MyAPP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"6ef214e1ac156cf351fcf72533a2aa2b");
    }
}
