package com.programmergabut.mvvm_generator_by_jiwo.data

import com.programmergabut.mvvm_generator_by_jiwo.base.BaseRepository
import com.programmergabut.mvvm_generator_by_jiwo.data.model.User
import com.programmergabut.mvvm_generator_by_jiwo.data.remote.api.SomeService
import com.programmergabut.mvvm_generator_by_jiwo.data.remote.remoteentity.SomeResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class Repository @Inject constructor(private val someService: SomeService): BaseRepository() {

    fun doLogin(user : User) : Deferred<SomeResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            lateinit var response : SomeResponse
            try{
                response = execute(someService.doLogin(
                    username = user.username,
                    password = user.password
                ))
            }
            catch(e : Exception){
                response = SomeResponse()
                response.status = "-1"
            }
            response
        }
    }

}