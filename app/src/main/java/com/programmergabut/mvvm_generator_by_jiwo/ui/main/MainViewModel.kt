package com.programmergabut.mvvm_generator_by_jiwo.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel
import com.programmergabut.mvvm_generator_by_jiwo.data.Repository
import com.programmergabut.mvvm_generator_by_jiwo.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(val repository: Repository): BaseViewModel() {

    private val user : MutableLiveData<User> = MutableLiveData()
    private val isLogin : MutableLiveData<Int> = MutableLiveData()

    fun setUser(user : User){
        this.user.value = user
    }

    fun getUser() : LiveData<User> {
        return user
    }

    fun isLogin() : LiveData<Int>{
        return isLogin
    }

    fun doLogin(){
        CoroutineScope(Dispatchers.IO).launch{
            val response = repository.doLoginAsync(user.value!!).await()
            when(response.status.toInt()){
                1 -> {
                    setUser(User(
                        user.value!!.username,
                        user.value!!.password
                    ))
                    isLogin.postValue(SUCCESS)
                }
                else -> {
                    setUser(User(
                        "",
                        ""
                    ))
                    isLogin.postValue(ERROR)
                }
            }
        }
    }
}