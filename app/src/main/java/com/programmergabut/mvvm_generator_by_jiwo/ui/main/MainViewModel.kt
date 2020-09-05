package com.programmergabut.mvvm_generator_by_jiwo.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.programmergabut.mvvm_generator_by_jiwo.base.BaseViewModel
import com.programmergabut.mvvm_generator_by_jiwo.data.Repository
import com.programmergabut.mvvm_generator_by_jiwo.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(val repository: Repository): BaseViewModel() {

    private val user : MutableLiveData<User> = MutableLiveData()
    private val isLogin : MutableLiveData<Int> = MutableLiveData()
    private var message : String = ""

    fun setUser(user : User){
        this.user.value = user
    }

    fun getUser() = user
    fun isLogin() = isLogin
    fun getMessage() = message

    fun doLogin(){

        CoroutineScope(Dispatchers.IO).launch{

            delay(2000)

            val response = repository.doLoginAsync(user.value!!).await()
            when(response.status.toInt()){
                1 -> {
                    User(user.value!!.username, user.value!!.password)
                    message = response.message
                    isLogin.postValue(SUCCESS)
                }
                else -> {
                    User("", "")
                    message = "Error while login because this is a dummy app's, but what's important is you can understand the way this app work" /* response.message */
                    isLogin.postValue(ERROR)
                }
            }
        }
    }
}