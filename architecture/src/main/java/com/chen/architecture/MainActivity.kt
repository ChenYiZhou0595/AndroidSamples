package com.chen.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        changeFragment(NormalFragment.newInstance())
        btnNormal.setOnClickListener { changeFragment(NormalFragment.newInstance()) }
        btnMVC.setOnClickListener { changeFragment(MVCFragment.newInstance()) }
        btnMVP.setOnClickListener { changeFragment(MVPFragment.newInstance()) }
        btnMVVM.setOnClickListener { changeFragment(MVVMFragment.newInstance()) }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
    }
}
