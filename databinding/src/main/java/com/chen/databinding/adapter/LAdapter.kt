package com.chen.databinding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chen.databinding.bean.ObUser
import com.chen.databinding.databinding.ItemLvBinding

/**
 * ListView的adapter，简单演示，所以也不写viewHolder了
 */
class LAdapter : BaseAdapter() {

    private var users: MutableList<ObUser> = arrayListOf()

    init {
        //初始化三个数据
        for (i in 0..2) {
            users.add(
                    ObUser("小明$i", 20 + i, i % 2, "小明小强从小学就伴随你一直到现在$i")
            )
        }
    }

    //简单演示，就不用viewHolder了。实际使用，不应该这样写
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemLvBinding.inflate(inflater)
        //java 写法
//            if (convertView == null) {
//              binding=  DataBindingUtil.inflate<ItemLvBinding>(inflater, R.layout.item_lv, parent, false);
//            } else {
//              binding-  DataBindingUtil.getBinding(convertView);
//            }
//        binding.setVariable(BR.user,users.get(position));

        binding.user = users[position]
        return binding.root
    }

    override fun getItem(position: Int): ObUser {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        //就简单演示3条数据
        return users.size
    }
}