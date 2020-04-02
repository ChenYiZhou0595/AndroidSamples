package com.chen.databinding.tools

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * todo Kotlin的一大特点，文件内可以多个类，函数。java则不能多个public的类，也不能函数。
 * 用于 xml 中 dataBinding 的静态函数.不同于 java 的 public static 函数写法。这里 kotlin 中有两种写法：
 * 1、直接定义在 kt 文件的 top level 顶级，直接写函数名。调用方导入 BindUtilKt，使用 BindUtilKt.ageName 应用。
 * 2、定义在一个静态类 object 中。在 kotlin中 就是 object 的静态类，或者 companion object 的类中。需要 @jvmStatic 标记才有效
 */

/**
 * 用于 xml 的 dataBinding 中，这是在 kt 文件顶级写法，不需要 static 标记
 */
fun ageName(age: Int, name: String): String {
    return "Kt 函数：$age$name"
}

fun toastV(context: Context) {
    Toast.makeText(context, "context弹出toast", Toast.LENGTH_LONG).show()
}

class BindUtil {

    companion object {

        @JvmStatic
        fun ageAge(age: Int): String {
            return "年龄$age"
        }
    }
}

object BindHelp {

    @JvmStatic
    fun nameName(name: String): String {
        return "姓名$name"
    }

    @JvmStatic
    fun staticClick(view: View) {
        Toast.makeText(view.context, "静态函数引用", Toast.LENGTH_LONG).show()
    }
}