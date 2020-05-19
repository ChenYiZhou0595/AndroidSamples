package com.chen.architecture.mvp

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface IView {
    fun setPresenter(presenter: IPresenter)
    fun loading()
    fun showData(result: String)
}