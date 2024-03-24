package com.spread.xdpnetwork.network.model

import com.google.gson.annotations.SerializedName

data class ThreadMeta(
    @SerializedName("absent") val absent: String,
    @SerializedName("content") val content: String,
    @SerializedName("createTime") val createTime: String,
    @SerializedName("highTag") val highTag: Int,
    @SerializedName("id") val id: Long,
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