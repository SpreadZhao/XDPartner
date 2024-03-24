package com.spread.xdpnetwork.network.service

import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.interceptor.LogInterceptor
import com.spread.xdpnetwork.network.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
  private val okHttpClient = OkHttpClient.Builder().addInterceptor(LogInterceptor()).addInterceptor(TokenInterceptor()).build()
  private val retrofit = Retrofit.Builder()
    .baseUrl(NetworkConstant.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}

