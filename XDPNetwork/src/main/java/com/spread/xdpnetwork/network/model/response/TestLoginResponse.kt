package com.spread.xdpnetwork.network.model.response

import com.google.gson.annotations.SerializedName
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.entry.UserBean
import com.spread.xdplib.adapter.entry.UserVo

data class TestLoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: UserBean,
    @SerializedName("msg") val msg: String
) : XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}

data class BlogsResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: List<Blog>,
    @SerializedName("msg") val msg: String
) : XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}

data class FriendsResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: List<UserVo>,
    @SerializedName("msg") val msg: String
) : XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}