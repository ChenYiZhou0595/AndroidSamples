package com.chen.architecture

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chen.architecture.mvc.*
import kotlinx.android.synthetic.main.fragment_architecture.*

class MVCFragment : Fragment(), IView {

    companion object {
        fun newInstance(): Fragment {
            return MVCFragment()
        }
    }

    private val model: IModel = HandleModel()
    private var controller: IController = HandleController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_architecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.setModel(model)
        model.setView(this)

        tvTitle.text = "MVC"
        etMsg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                controller.onDataChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        btnClear.setOnClickListener { controller.clearData() }
    }

    override fun setController(controller: IController) {
        this.controller = controller
    }

    override fun dataHanding() {
        tvMsg.text = "handle data ..."
    }

    override fun onDataHandled(result: String) {
        tvMsg.text = result
    }
}
