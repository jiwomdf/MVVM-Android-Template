package com.programmergabut.mvvm_generator_by_jiwo.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.programmergabut.mvvm_generator_by_jiwo.R
import com.programmergabut.mvvm_generator_by_jiwo.databinding.LayoutBottomSheetBinding
import com.programmergabut.mvvm_generator_by_jiwo.util.SharedPrefUtil

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding>(private val layout : Int): Fragment() {

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected lateinit var binding : DB
    private lateinit var sharedPrefUtil: SharedPrefUtil

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = this

        setListener()

        // Shared Preference
        context?.let { context -> sharedPrefUtil = SharedPrefUtil(context) }

        return binding.root
    }

    protected fun <T : Any> gotoIntent(classIntent : Class<T>, bundle : Bundle?, isFinish : Boolean){
        val intent = Intent(this.activity, classIntent)
        if(bundle != null)
            intent.putExtras(bundle)
        startActivity(intent)
        if(isFinish)
            activity?.finish()
    }

    protected fun showBottomSheet(title : String = resources.getString(R.string.text_error), description : String = "",
                                  btnText: String = "Oke", isCancelable : Boolean = true, isFinish : Boolean = false) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())

        val bsBinding : LayoutBottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.layout_bottom_sheet, null, false)
        bsBinding.title = title
        bsBinding.description = description
        bsBinding.btnText = btnText
        bsBinding.btnOk.setOnClickListener{
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setCancelable(isCancelable)
        bottomSheetDialog.setContentView(bsBinding.root)
        bottomSheetDialog.show()
    }

    open fun setListener(){

    }

}