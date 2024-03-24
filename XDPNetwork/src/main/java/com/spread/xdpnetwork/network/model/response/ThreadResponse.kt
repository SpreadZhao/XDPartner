package com.spread.xdpnetwork.network.model.response

import com.google.gson.annotations.SerializedName
import com.spread.xdpnetwork.network.model.ThreadMeta

data class ThreadResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: ThreadMeta,
    @SerializedName("msg") val msg: String
) : XDPartnerResponse {
  override fun code() = code

  override fun msg() = msg
}