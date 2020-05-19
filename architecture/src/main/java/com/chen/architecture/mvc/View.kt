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

interface IView {
    fun setController(controller: IController)
    fun dataHanding()
    fun onDataHandled(result: String)
}