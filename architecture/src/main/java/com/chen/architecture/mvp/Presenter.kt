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
interface IPresenter {
    fun setView(view: IView)
    fun setModel(model: IModel)
    fun onTextChanged(data: String)
    fun onDataHandled(result: String)
    fun onClearButtonClicked()
    fun onDataCleared()
}

class Presenter : IPresenter {

    private lateinit var view: IView
    private lateinit var model: IModel

    override fun setView(view: IView) {
        this.view = view
    }

    override fun setModel(model: IModel) {
        this.model = model
    }

    override fun onTextChanged(data: String) {
        view.loading()
        model.handleData(data)
    }

    override fun onDataHandled(result: String) {
        view.showData(result)
    }

    override fun onClearButtonClicked() {
        model.clearData()
    }

    override fun onDataCleared() {
        view.showData("Default Msg")
    }
}