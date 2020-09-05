package com.programmergabut.mvvm_generator_by_jiwo.ui.main.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseFragment
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel
import com.programmergabut.mvvm_generator_by_jiwo.databinding.FragmentFirstBinding
import com.programmergabut.mvvm_generator_by_jiwo.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<MainViewModel, FragmentFirstBinding>(R.layout.fragment_first) {

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setListener() {
        super.setListener()

        viewModel.isLogin().observe(this, Observer { status ->

            viewModel.loading.value = BaseViewModel.REMOVE_LOADING

            when(status){
                BaseViewModel.SUCCESS -> {

                }
                BaseViewModel.ERROR -> {

                }
            }
        })
    }
}