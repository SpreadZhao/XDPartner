package com.spread.xdpartner.network.model

import com.google.gson.annotations.SerializedName

data class ThreadsResponse(
  @SerializedName("code") val code: Int,
  @SerializedName("data") val data: List<ThreadMeta>,
  @SerializedName("msg") val msg: String
)

data class ThreadMeta(
  @SerializedName("absent") val absent: String,
  @SerializedName("content") val content: String,
  @SerializedName("createTime") val createTime: String,
  @SerializedName("highTag") val highTag: Int,
  @SerializedName("images") val imagesUrlList: List<String>,
  @SerializedName("isComplete") val isComplete: Int,
  @SerializedName("isLiked") val isLiked: Boolean,
  @SerializedName("liked") val liked: Int,
  @SerializedName("location") val location: String,
  @SerializedName("lowTags") val lowTags: List<String>,
  @SerializedName("title") val title: String,
  @SerializedName("updateTime") val updateTime: String,
  @SerializedName("userVo") val userProfile: UserProfile,
  @SerializedName("viewTimes") val viewTimes: Int,
  @SerializedName("whenMeet") val whenMeet: String
)

data class UserProfile(
  @SerializedName("icon") val iconUrl: String,
  @SerializedName("id") val id: Int,
  @SerializedName("nickName") val nickName: String
)
