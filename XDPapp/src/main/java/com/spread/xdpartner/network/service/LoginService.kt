package com.spread.xdpartner.network.service

import com.spread.xdpartner.network.model.LoginBean
import com.spread.xdpartner.network.model.response.ThreadsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/wz/user/login")
    fun login(
        @Body bean : LoginBean
    ): Call<ThreadsResponse>

}