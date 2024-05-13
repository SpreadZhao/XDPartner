package com.spread.xdpnetwork.network.model.response

import com.google.gson.annotations.SerializedName
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.entry.Message
import com.spread.xdplib.adapter.entry.MessageFiendBean
import com.spread.xdplib.adapter.entry.PolicyBean
import com.spread.xdplib.adapter.entry.UserBean
import com.spread.xdplib.adapter.entry.UserDetail
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

data class BaseResponse(@SerializedName("code") val code: Int,
                        @SerializedName("data") val data: String,
                        @SerializedName("msg") val msg: String): XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}

data class UserResponse(@SerializedName("code") val code: Int,
                        @SerializedName("data") val data: UserDetail,
                        @SerializedName("msg") val msg: String): XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}
data class PolicyResponse(@SerializedName("code") val code: Int,
                          @SerializedName("data") val data: PolicyBean,
                          @SerializedName("msg") val msg: String): XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}

data class ConnectResponse(@SerializedName("code") val code: Int,
                          @SerializedName("data") val data: List<MessageFiendBean>,
                          @SerializedName("msg") val msg: String): XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}

data class MessageResponse(@SerializedName("code") val code: Int,
                           @SerializedName("data") val data: List<Message>,
                           @SerializedName("msg") val msg: String): XDPartnerResponse {
    override fun code() = code

    override fun msg() = msg
}
