package com.programmergabut.mvvm_generator_by_jiwo.base

import android.util.Log
import com.google.gson.Gson
import com.programmergabut.mvvm_generator_by_jiwo.BuildConfig
import com.programmergabut.mvvm_generator_by_jiwo.Constants
import retrofit2.Call

abstract class BaseRepository{

    protected fun<T : BaseResponse> execute(call : Call<T>) : T {
        try{
            val response = call.execute()
            return when(response.isSuccessful){
                true -> {
                    if(BuildConfig.BUILD_TYPE == ("debug"))
                        Log.d("<RES>", Gson().toJson(response.body()!!))
                    response.body()!!
                }
                false -> {
                    if(BuildConfig.BUILD_TYPE == "debug")
                        Log.d("<RES>", response.message())
                    throw Exception()
                }
            }
        }
        catch (e : Exception){
            if(BuildConfig.BUILD_TYPE == "debug")
                e.message?.let {
                    Log.d("<RES>", it)
                }
            throw e
        }
    }
}