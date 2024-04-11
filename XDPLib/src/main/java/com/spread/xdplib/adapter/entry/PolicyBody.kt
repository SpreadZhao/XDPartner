package com.spread.xdplib.adapter.entry

import com.google.gson.annotations.SerializedName
import java.io.File

data class PolicyBody(
    @SerializedName("key") val key: String,
    @SerializedName("policy") val policy: String,
    @SerializedName("signature") val signature: String,
    @SerializedName("success_action_status") val success_action_status: String,
    @SerializedName("OSSAccessKeyId") val OSSAccessKeyId: String,
    @SerializedName("file") val file: File
)