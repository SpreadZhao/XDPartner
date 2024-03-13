package com.spread.xdpnetwork.network.service

import com.spread.xdpnetwork.network.model.LoginBean
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/wz/user/login")
    fun login(
        @Body bean : LoginBean
    ): Call<ThreadsResponse>

}