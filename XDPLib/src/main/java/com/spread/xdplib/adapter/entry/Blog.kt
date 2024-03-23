package com.spread.xdplib.adapter.entry

import com.google.gson.annotations.SerializedName


data class Blog (
    @SerializedName("absent") val absent: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("content") val content: String,
    @SerializedName("createTime") val createTime: String,
    @SerializedName("highTag") val highTag: String,
    @SerializedName("id") val id: Long,
    @SerializedName("images") val images: List<String>,
    @SerializedName("isComplete")  val isComplete: Long,
    @SerializedName("isLiked")  val isLiked: Boolean,
    @SerializedName("liked")  val liked: Long,
    @SerializedName("location")   val location: String,
    @SerializedName("lowTags")  val lowTags: List<String>,
    @SerializedName("title")  val title: String,
    @SerializedName("updateTime")   val updateTime: String,
    @SerializedName("userVo")   val userVo: UserVo,
    @SerializedName("viewTimes")  val viewTimes: Long,
    @SerializedName("whenMeet")  val whenMeet: String
)

data class UserVo (
    @SerializedName("icon") val icon: String,
    @SerializedName("id")  val id: Long,
    @SerializedName("nickName")  val nickName: String
)