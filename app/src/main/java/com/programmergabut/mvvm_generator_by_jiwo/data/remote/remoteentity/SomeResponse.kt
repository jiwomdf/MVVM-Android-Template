package com.programmergabut.mvvm_generator_by_jiwo.data.remote.remoteentity

import com.programmergabut.mvvm_generator_by_jiwo.base.BaseResponse

data class SomeResponse (
    val username: String = "",
    val password: String = ""
): BaseResponse()