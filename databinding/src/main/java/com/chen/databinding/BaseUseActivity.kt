package com.chen.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chen.databinding.databinding.ActivityBaseUseBinding
import com.chen.databinding.tools.BindHelp

class BaseUseActivity: AppCompatActivity() {

    /**
     * 简要步骤：
     * 1. build.gradle 中 android 闭包下，配置 dataBinding { enabled = true }
     * 2. 对于布局的 xml 文件，将原有的正常布局，外面用 <layout></layout> 包裹作为根节点
     *    <data></data> 节点下存放用于 xml 布局的一些变量，工具类之类的
     * 3. 代码无误的情况下，build 一下 module 或整个 project 。
     */

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        // Activity 使用 DataBindingUtil.setContentView 关联 xml 布局文件，替代原有的 setContentView 方式。
        // ActivityBaseUseBinding 为 DataBinding 根据 xml 自动生成的类文件
        val binding = DataBindingUtil.setContentView<ActivityBaseUseBinding>(this, R.layout.activity_base_use)
        // 设置在 xml 中声明的变量值
        binding.age = 11
        binding.isStudent = true
        binding.name = "BindName"
        binding.title = "BD标题"
        // list map
        val ages = listOf("20", "18", "19")
        val map = mapOf(19 to "Lily", 21 to "Jim", 20 to "Aili")
        binding.ages = ages
        binding.map = map
        // 静态点击的 helper
        binding.helper = BindHelp
    }
}
