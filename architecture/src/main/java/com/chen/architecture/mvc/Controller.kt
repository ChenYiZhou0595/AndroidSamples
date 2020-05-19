package com.chen.architecture.mvc

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class HandleController : IController {

    private lateinit var model: IModel

    override fun setModel(model: IModel) {
        this.model = model
    }

    override fun onDataChanged(data: String) {
        model.handleData(data)
    }

    override fun clearData() {
        model.clearData()
    }

}

interface IController {
    fun setModel(model: IModel)
    fun onDataChanged(data: String)
    fun clearData()
}