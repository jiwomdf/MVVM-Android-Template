package com.programmergabut.mvvm_generator_by_jiwo.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.databinding.LayoutErrorBinding
import com.programmergabut.mvvm_generator_by_jiwo.util.SharedPrefUtil

abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding>(private val layout : Int): AppCompatActivity() {

    protected val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected lateinit var binding : DB
    private lateinit var root : ViewGroup
    private lateinit var loader : View
    private lateinit var sharedPrefUtil: SharedPrefUtil

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this

        // Loader
        root = findViewById(android.R.id.content)
        loader = LayoutInflater.from(this).inflate(R.layout.loader, null, false)

        // Shared Preference
        sharedPrefUtil = SharedPrefUtil(this)

        setListener()
    }

    open protected fun setListener(){
        (viewModel as BaseViewModel).loading.observe(this, Observer { status ->
            when(status){
                BaseViewModel.SHOW_LOADING -> loading(true)
                BaseViewModel.REMOVE_LOADING -> loading(false)
            }
        })
    }

    private fun loading(isShow : Boolean){
        root.removeView(loader)
        if(isShow){
            root.addView(loader)
        }
    }

    protected fun <T : Any> gotoIntent(classIntent : Class<T>, bundle : Bundle?, isFinish : Boolean){
        val intent = Intent(this, classIntent)
        if(bundle != null)
            intent.putExtras(bundle)
        startActivity(intent)
        if(isFinish)
            finish()
    }

    protected fun   showError(title : String = resources.getString(R.string.text_error), description : String = "", isCancelable : Boolean = true, isFinish : Boolean = false) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val errorBinding : LayoutErrorBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_error, null, false)
        errorBinding.title = title
        errorBinding.description = description
        errorBinding.btnOk.setOnClickListener{
            bottomSheetDialog.dismiss()
            if(isFinish)
                finish()
        }
        bottomSheetDialog.setCancelable(isCancelable)
        bottomSheetDialog.setContentView(errorBinding.root)
        bottomSheetDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        loading(false)
    }
}