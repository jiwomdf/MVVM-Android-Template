package com.programmergabut.mvvm_generator_by_jiwo.ui.main.firstfragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseFragment
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel
import com.programmergabut.mvvm_generator_by_jiwo.data.model.User
import com.programmergabut.mvvm_generator_by_jiwo.databinding.FragmentFirstBinding
import com.programmergabut.mvvm_generator_by_jiwo.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<MainViewModel, FragmentFirstBinding>(R.layout.fragment_first) {

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setUser(User("",""))
        binding.viewModel = viewModel
    }

    override fun setListener() {
        super.setListener()

        binding.btnLogin.setOnClickListener {
            binding.loading.visibility = View.VISIBLE

            viewModel.doLogin()
        }

        viewModel.isLogin().observe(this, Observer { status ->

            binding.loading.visibility = View.GONE

            when(status){
                BaseViewModel.SUCCESS ->{
                    //do something
                }
                BaseViewModel.ERROR ->{
                    showBottomSheet("Error Login", viewModel.getMessage(), getString(R.string.understood), isCancelable = true, isFinish = false)
                }
            }
        })
    }
}