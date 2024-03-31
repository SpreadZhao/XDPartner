package com.spread.xdpnetwork.network

import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.model.response.ThreadResponse
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import com.spread.xdpnetwork.network.model.response.XDPartnerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TestCallBackManager {

  private val TAG = "TestCallbackManager"

  val threadsCallback = object : BasicThreadingCallback<ThreadsResponse>({
    logd("response body size: ${it.body()?.data?.size}")
    logd("Response body: ${it.body()}")
  }) {

  }

  val threadCallback = object : BasicThreadingCallback<ThreadResponse>({
    logd("response body: ${it.body()}")
  }) {}
}

open class BasicThreadingCallback<T : XDPartnerResponse>(
  private val logDataFunc: (Response<T>) -> Unit
) : Callback<T> {
  override fun onResponse(call: Call<T>, response: Response<T>) {
    logd("Response code: ${response.code()}")
    logd("Response body code: ${response.body()?.code()}")
    logd("Response msg: ${response.body()?.msg()}")
    logDataFunc(response)
  }

  override fun onFailure(call: Call<T>, t: Throwable) {
    logd("failure ${t.message} + , + ${t.javaClass}" )
    t.printStackTrace()
  }
}