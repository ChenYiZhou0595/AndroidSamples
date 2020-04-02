package com.chen.databinding.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.library.baseAdapters.BR

/**
 * 用于 DataBinding 的数据对象类，user，
 * 这里在 kotlin 中，多个 public 的 class 可以放在一个 kt 文件中
 */
data class CommonUser(
        val name: String,
        var age: Int,
        var sex: Int,
        var desc: String
)

/**
 * 这是部分属性可以 dataBinding 响应的 user 类，注意 observable 的属性需要 public 权限，否则 dataBinding 则无法通过反射处理数据响应
 */
data class FieldUser(
        var name: String,
        var age: ObservableInt,
        var sex: Int,
        var desc: ObservableField<String>
)

/**
 * 继承dataBinding的baseObservable的user对象类
 * 这里是kotlin的写法，类似于java中，继承BaseObservable的对象类，
 * 需要响应变化的字段，就在对应变量的get函数上加 @Bindable 。然后set中notifyChange kotlin的写法，免去了java的getter setter的方式
 * 成员属性需要响应变化的，就在其set函数中，notify一下属性变化，那么set的时候，databinding就会感知到。
 */
class ObUser() : BaseObservable() {
    //kotlin中类的构造函数可以多个，有主次之分，且次级构造函数必须调用主构造函数，如这里的this()
    constructor(name: String, age: Int, sex: Int, desc: String) : this() {
        this.name = name
        this.age = age
        this.desc = desc
    }


    //这是单独在set上@bindable，name可以声明private
    var name: String = ""
        //kotlin的成员属性必须初始化（或者lateinit）
        set(value) {
            //BR.name表示通知name这个属性的变化。 notifyChange() 通知所有变化
            notifyPropertyChanged(BR.name)
            field = value
        }
        @Bindable
        get() = field

    //这是在整个变量上声明@bindable，所以必须是public的
    @Bindable
    var age = 18
        set(value) {
            notifyPropertyChanged(BR.age)
            field = value
        }
        get() = field

    val sex = 1

    var desc: String = ""
        set(value) {
            field = "$value\n set中多余的拼接"//描述
            notifyPropertyChanged(BR.desc)
        }
        @Bindable
        get() {
            return field
        }

    override fun toString(): String {
        notifyChange()
        return "$name $age $sex $desc"
    }
}