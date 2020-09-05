package com.programmergabut.mvvm_generator_by_jiwo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    companion object {
        const val SHOW_LOADING : Int = 1
        const val REMOVE_LOADING : Int = 2

        const val SUCCESS : Int = 3
        const val ERROR : Int = 4
    }
    val loading : MutableLiveData<Int> = MutableLiveData()
}