package com.chen.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chen.databinding.bean.ObUser
import com.chen.databinding.databinding.ItemLvBinding

/**
 * 简单演示的RecyclerView的adapter
 */
class RAdapter : RecyclerView.Adapter<RAdapter.MyHolder>() {

	private var users: MutableList<ObUser> = arrayListOf()

	init {
		//初始化三个数据
		for (i in 0..2) {
			users.add(
				ObUser("LiLy$i", 20 + i, i % 2, "LiLy Jim are friends $i")
			)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemLvBinding.inflate(inflater)
		return MyHolder(binding)
	}

	//kotlin中，return的方式，可以简写
	override fun getItemCount() = users.size


	override fun onBindViewHolder(holder: MyHolder, position: Int) {
		//java 写法可以setVariable
		holder.binding.user = users[position]
		holder.binding.executePendingBindings()
	}

	//在构造函数中声明binding变量，这样上面的holder才能引用到，如果不加val/var，就引用不到，就需要在class的{}内写get函数
	class MyHolder(val binding: ItemLvBinding) : RecyclerView.ViewHolder(binding.root)
}