package com.chen.architecture.mvvm

import androidx.lifecycle.MutableLiveData

interface IViewModel {
    fun setModel(model: IModel)
    fun handleText(text: String)
    fun clearData()
    fun dataHandled(result: String)
    fun dataCleared()
}

class ViewModel : IViewModel {
    private lateinit var model: IModel
    var inputText: MutableLiveData<String> = MutableLiveData()
    var handledText: MutableLiveData<String> = MutableLiveData()

    init {
        inputText.observeForever {
            handleText(it)
        }
        handledText.value = "Default Msg"
    }

    override fun handleText(text: String) {
        handledText.value = "Handle Data ..."
        model.handleData(text)
    }

    override fun clearData() {
        model.clearData()
    }

    override fun setModel(model: IModel) {
        this.model = model
        model.setViewModel(this)
    }

    override fun dataHandled(result: String) {
        handledText.value = result
    }

    override fun dataCleared() {
        inputText.value = ""
    }
}
