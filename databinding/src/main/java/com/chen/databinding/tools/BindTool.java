package com.chen.databinding.tools;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BindTool {

    /**
     * 简单的一个静态函数，dataBinding 在 xml 中使用的函数，必须是 public 的 static 函数
     *
     * @param name 名称
     * @param age  年龄
     * @return string的拼接
     */
    public static String nameAge(String name, int age) {
        return name + age;
    }

    /**
     * 点击就打印一个log
     */
    public static void log() {
        Log.i("BindTool", "dataBinding的普通点击，静态java函数的log日志");
    }

    /**
     * 使用view参数，显示toast
     *
     * @param view view控件
     */
    public static void toast(View view) {
        Toast.makeText(view.getContext(), "普通点击view显示toast", Toast.LENGTH_SHORT).show();
    }
}
