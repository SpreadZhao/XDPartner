package com.spread.xdpnetwork.network

import com.spread.xdplib.adapter.utils.TestLogger.log
import com.spread.xdpnetwork.network.model.response.ThreadResponse
import com.spread.xdpnetwork.network.model.response.ThreadsResponse
import com.spread.xdpnetwork.network.model.response.XDPartnerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TestCallBackManager {

  private val TAG = "TestCallbackManager"

  val threadsCallback = object : BasicThreadingCallback<ThreadsResponse>({
    log("response body size: ${it.body()?.data?.size}")
    log("Response body: ${it.body()}")
  }) {

  }

  val threadCallback = object : BasicThreadingCallback<ThreadResponse>({
    log("response body: ${it.body()}")
  }) {}
}

open class BasicThreadingCallback<T : XDPartnerResponse>(
  private val logDataFunc: (Response<T>) -> Unit
) : Callback<T> {
  override fun onResponse(call: Call<T>, response: Response<T>) {
    log("Response code: ${response.code()}")
    log("Response body code: ${response.body()?.code()}")
    log("Response msg: ${response.body()?.msg()}")
    logDataFunc(response)
  }

  override fun onFailure(call: Call<T>, t: Throwable) {
    log("failure ${t.message} + , + ${t.javaClass}" )
    t.printStackTrace()
  }
}