package com.spread.xdplib.adapter.entry

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("constellation") val constellation: Int,
    @SerializedName("highTag") val highTag: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("majorName") val majorName: String,
    @SerializedName("mbti") val mbti: Int,
    @SerializedName("myDescription")  val myDescription: String,
    @SerializedName("nickName")  val nickName: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("qq") val qq: String
)

