package com.spread.xdplib.adapter.entry

import com.google.gson.annotations.SerializedName

data class PolicyBean (
    @SerializedName("accessid") val accessid: String,
    @SerializedName("policy") val policy: String,
    @SerializedName("signature") val signature: String,
    @SerializedName("dir") val dir: String,
    @SerializedName("host") val host: String,
    @SerializedName("expire") val expire: String
)