package com.programmergabut.mvvm_generator_by_jiwo.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseActivity
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel.Companion.ERROR
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel.Companion.SUCCESS
import com.programmergabut.mvvm_generator_by_jiwo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main){

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNav()
    }

    private fun initBottomNav() {

        try{
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            binding.bottomMenu.setupWithNavController(navController)

            navHostFragment.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                    when(destination.id){
                        R.id.menu_firstFragment, R.id.menu_secondFragment ->
                            binding.bottomMenu.visibility = View.VISIBLE
                        else ->
                            binding.bottomMenu.visibility = View.GONE
                    }
                }
        }catch (ex: Exception){
            print(ex.message)
        }

    }

    override fun setListener() {
        super.setListener()

    }

}