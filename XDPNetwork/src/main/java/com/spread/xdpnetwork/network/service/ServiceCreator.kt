package com.spread.xdpnetwork.network.service

import com.google.gson.GsonBuilder
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdpnetwork.network.NetworkConstant
import com.spread.xdpnetwork.network.gson.UserDetailDeserializer
import com.spread.xdpnetwork.network.interceptor.BaseUrlInterceptor
import com.spread.xdpnetwork.network.interceptor.LogInterceptor
import com.spread.xdpnetwork.network.interceptor.TokenInterceptor
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import org.jsoup.Connection.Base
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    val baseUrlInterceptor = BaseUrlInterceptor()
    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(baseUrlInterceptor)
            .addInterceptor(TokenInterceptor()).addInterceptor(LogInterceptor())
            .build()
    private val gson = GsonBuilder()
        .registerTypeAdapter(UserDetail::class.java, UserDetailDeserializer())
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkConstant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


// 然后使用这个 Gson 实例来配置 Retrofit

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    fun setNewUrl(url: String) {
        baseUrlInterceptor.setNewUrl(url)
    }
}

