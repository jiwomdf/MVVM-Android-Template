package com.programmergabut.mvvm_generator_by_jiwo.ui.main.secondfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseFragment
import com.programmergabut.mvvm_generator_by_jiwo.databinding.FragmentFirstBinding
import com.programmergabut.mvvm_generator_by_jiwo.databinding.FragmentSecondBinding
import com.programmergabut.mvvm_generator_by_jiwo.ui.main.MainViewModel

class SecondFragment : BaseFragment<MainViewModel, FragmentSecondBinding>(R.layout.fragment_second) {

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.data = getString(R.string.second_fragment)
    }

    override fun setListener() {
        super.setListener()

    }
}