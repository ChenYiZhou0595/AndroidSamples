package com.chen.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chen.architecture.databinding.FragmentArchitectureBindingBinding
import com.chen.architecture.mvvm.HandleModel
import com.chen.architecture.mvvm.ViewModel

class MVVMFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return MVVMFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentArchitectureBindingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_architecture_binding, container, false)
        binding.lifecycleOwner = this
        val viewModel = ViewModel()
        viewModel.setModel(HandleModel())
        binding.viewModel = viewModel
        return binding.root
    }
}
