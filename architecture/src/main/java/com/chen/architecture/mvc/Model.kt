package com.chen.architecture.mvc

import android.os.Handler
import android.os.Looper
import android.text.TextUtils

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class HandleModel : IModel {

    private lateinit var view: IView
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun setView(view: IView) {
        this.view = view
    }

    override fun handleData(data: String) {
        view.dataHanding()
        handler.removeCallbacksAndMessages(null)
        // 延迟来模拟网络或者磁盘操作
        handler.postDelayed({
            val result: String = if (TextUtils.isEmpty(data)) {
                "Default Msg"
            } else {
                "Handled data: $data"
            }
            view.onDataHandled(result)
        }, 3000)
    }

    override fun clearData() {
        handler.removeCallbacksAndMessages(null)
        view.onDataHandled("Clear Success")
    }

}

interface IModel {
    fun setView(view: IView)
    fun handleData(data: String)
    fun clearData()
}