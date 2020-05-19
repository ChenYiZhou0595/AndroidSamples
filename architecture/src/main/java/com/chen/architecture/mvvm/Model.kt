package com.chen.architecture.mvvm

import android.os.Handler
import android.os.Looper
import android.text.TextUtils

class HandleModel : IModel {
    private lateinit var viewModel: IViewModel
    private var handler = Handler(Looper.getMainLooper())

    override fun handleData(data: String?) {
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            val result: String = if (TextUtils.isEmpty(data)) {
                "Default Msg"
            } else {
                "Handled data: $data"
            }
            viewModel.dataHandled(result)
        }, 3000)
    }

    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        viewModel.dataCleared()
    }

    override fun setViewModel(viewModel: IViewModel) {
        this.viewModel = viewModel
    }
}

interface IModel {
    fun setViewModel(viewModel: IViewModel)
    fun handleData(data: String?)
    fun clearData()
}
