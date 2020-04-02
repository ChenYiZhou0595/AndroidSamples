package com.chen.databinding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.chen.databinding.adapter.LAdapter
import com.chen.databinding.adapter.RAdapter
import com.chen.databinding.bean.CommonUser
import com.chen.databinding.bean.FieldUser
import com.chen.databinding.bean.ObUser
import com.chen.databinding.databinding.ActivityCommonUseBinding
import kotlinx.android.synthetic.main.activity_common_use.*

class CommonUseActivity : AppCompatActivity(), View.OnClickListener {

    //<editor-folder desc="成员变量代码块">

    // 懒加载初始化控件
    private val tvCommon: AppCompatTextView by lazy { findViewById<AppCompatTextView>(R.id.tv_common_user_info) }

    // apply plugin: 'kotlin-android-extensions' 可直接使用 tv_field_user_info
    private val tvField: AppCompatTextView by lazy { tv_field_user_info }

    // 稍后初始化
    private lateinit var tvObservable: AppCompatTextView

    private lateinit var user: CommonUser // 普通 user 对象
    private lateinit var fuser: FieldUser // 部分属性可变的 user
    private lateinit var obuser: ObUser // observable 的 user

    //</editor-folder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCommonUseBinding>(this, R.layout.activity_common_use)

        binding.btnChangeCommon.setOnClickListener(this)
        binding.btnChangeField.setOnClickListener(this)
        binding.btnChangeOb.setOnClickListener(this)

        user = CommonUser("张三", 33, 1, "张家的牌面")
        binding.user = user

        fuser = FieldUser(
                "李四",
                ObservableInt(44),
                1,
                ObservableField<String>("李四是谁，究竟是哪个李四")
        )
        // fuser.desc.get()//在代码中要获取desc这样的observable的属性，就需要用get()，而且get()的是可null的返回，
        // 在xml中则直接用user.desc即可，不需要写.get()。写了也无妨
        binding.fuser = fuser

        obuser = ObUser("王二", 22, 0, "你会看到，这个王二的性别，你是改不了的，因为内部val声明了不可变量")
        binding.ouser = obuser
        //lateinit延迟初始化
        tvObservable = tv_ob_user_info
        //checkbox的响应
        cb_common.setOnCheckedChangeListener { button, checked ->
            //设置变量
            binding.show = checked
        }
        //adapter设置
        binding.ladapter = LAdapter()
        binding.radapter = RAdapter()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_change_common -> {
                user.age = 10;
                user.desc = "张三的改变 desc，普通 user 对象不会响应 binding"
                Toast.makeText(this, "注意上面的 common user 的 info 信息不会变化", Toast.LENGTH_SHORT).show()
                // 当然，如果你在此处,显式的代码设置text，会变化。
                // 但是这不是数据绑定，因为你每次改变都要setText才生效
                // tvCommon.text = user.toString()
            }
            R.id.btn_change_field -> {
                fuser.name = "李四/李斯---"
                fuser.age.set(28)
                fuser.sex = 0
                fuser.desc.set("age 和 desc 可以改变，但是为什么 name 和 sex 改变也可以生效，待研究")
            }
            R.id.btn_change_ob -> {
                obuser.name = "王二二"
                obuser.age = 222
                obuser.desc = "王二的名字，年龄，描述都会响应binding的变化"
            }
            else -> {
                //do nothing
            }
        }
    }
}