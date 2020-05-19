package com.chen.architecture

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chen.architecture.mvp.HandleModel
import com.chen.architecture.mvp.IPresenter
import com.chen.architecture.mvp.IView
import com.chen.architecture.mvp.Presenter
import kotlinx.android.synthetic.main.fragment_architecture.*

class MVPFragment : Fragment(), IView {

    companion object {
        fun newInstance(): Fragment {
            val presenter = Presenter()
            val fragment = MVPFragment()
            val model = HandleModel()
            presenter.setView(fragment)
            presenter.setModel(model)
            fragment.setPresenter(presenter)
            model.setPresenter(presenter)
            return fragment
        }
    }

    private lateinit var presenter: IPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_architecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = "MVP"

        etMsg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.onTextChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        btnClear.setOnClickListener { presenter.onClearButtonClicked() }
    }

    override fun setPresenter(presenter: IPresenter) {
        this.presenter = presenter
    }

    override fun loading() {
        tvMsg.text = "handle data ..."
    }

    override fun showData(result: String) {
        tvMsg.text = result
    }
}
