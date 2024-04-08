package com.spread.xdplib.adapter.entry

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetail(
    @SerializedName("constellation") val constellation: Int,
    @SerializedName("highTag") val highTag: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("majorName") val majorName: String = "未设置",
    @SerializedName("mbti") val mbti: Int,
    @SerializedName("myDescription")  val myDescription: String,
    @SerializedName("nickName")  val nickName: String,
    @SerializedName("images") val images: List<String> = emptyList(),
    @SerializedName("qq") val qq: String
) : Parcelable{

}

