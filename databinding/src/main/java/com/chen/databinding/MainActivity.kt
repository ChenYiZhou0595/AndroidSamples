package com.chen.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNotice()
    }

    fun baseUse(v: View) {
        // kotlin 类对应的 java 类文件为 ::class.java
        // .class 便是自身的 Kotlin 类
        startActivity(Intent(this, BaseUseActivity::class.java))
    }

    fun commonUse(v: View) {
        // 如果 this 表示不明晰，可以 @ 指定类名，即可明确对象指引
        //startActivity(Intent(this@MainActivity, CommonUseActivity::class.java))
    }

    fun advancedUse(v: View) {
        //startActivity(Intent(this, AdvancedUseActivity::class.java))
    }

    private fun setNotice() {

    }
}
