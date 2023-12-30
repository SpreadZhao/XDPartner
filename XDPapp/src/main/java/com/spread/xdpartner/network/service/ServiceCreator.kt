package com.spread.xdpartner.network.service

import com.spread.xdpartner.network.NetworkConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

  private val retrofit = Retrofit.Builder()
    .baseUrl(NetworkConstant.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}