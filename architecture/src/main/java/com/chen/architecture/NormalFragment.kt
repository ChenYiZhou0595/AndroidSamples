package com.chen.architecture

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_architecture.*

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NormalFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return NormalFragment()
        }
    }

    private val handler: Handler = Handler();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_architecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = "NORMAL"
        etMsg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                handleData(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        btnClear.setOnClickListener { etMsg.setText("") }
    }

    private fun handleData(data: String) {
        if (TextUtils.isEmpty(data)) {
            tvMsg.text = "Default Msg"
            return
        }
        tvMsg.text = "handle data ..."
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            tvMsg.text = "handled data: $data"
        }, 3000)
    }
}