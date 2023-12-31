package com.spread.xdpartner.network.model

import com.google.gson.annotations.SerializedName

data class UserProfile(
  @SerializedName("icon") val iconUrl: String,
  @SerializedName("id") val id: Int,
  @SerializedName("nickName") val nickName: String
)
