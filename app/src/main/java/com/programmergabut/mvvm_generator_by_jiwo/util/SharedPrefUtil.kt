package com.programmergabut.mvvm_generator_by_jiwo.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtil(private val context : Context) {

    private var sharedPreferences : SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("mvvm_jiwo_shared_pref", Context.MODE_PRIVATE)
    }


}