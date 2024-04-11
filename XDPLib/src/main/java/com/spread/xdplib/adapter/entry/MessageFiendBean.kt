package com.spread.xdplib.adapter.entry

import com.google.gson.annotations.SerializedName

data class MessageFiendBean(
    @SerializedName("count") val count: Int,
    @SerializedName("isFriend") val isFriend: Int,
    @SerializedName("content") val content: String,
    @SerializedName("userVo")   val userVo: UserVo,
    @SerializedName("messages")   val messages: List<Message> = emptyList(),
)
data class Message(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val isFriend: Int,
    @SerializedName("fromId") val fromId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("createTime")   val createTime: String,
)
