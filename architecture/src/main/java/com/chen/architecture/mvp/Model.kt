package com.chen.architecture.mvp

import android.os.Handler
import android.os.Looper
import android.text.TextUtils

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface IModel {
    fun setPresenter(presenter: IPresenter)
    fun handleData(data: String)
    fun clearData()
}

class HandleModel : IModel {

    private lateinit var presenter: IPresenter
    private var handler = Handler(Looper.getMainLooper())

    override fun setPresenter(presenter: IPresenter) {
        this.presenter = presenter
    }

    override fun handleData(data: String) {
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            val result: String = if (TextUtils.isEmpty(data)) {
                "Default Msg"
            } else {
                "Handled data: $data"
            }
            presenter.onDataHandled(result)
        }, 3000)
    }

    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        presenter.onDataCleared()
    }

}