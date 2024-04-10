package com.spread.xdplib.adapter.utils

import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object StringUtils {
    /**
     * 获取blog人数，时间，地点对应text
     */
    fun getBlogPeopleText(people:String?,time:String?,location:String?):String{
        val res =""
        if(people.isNullOrEmpty()){
            return res
        }
        return if(time.isNullOrEmpty()){
            res + people
        } else{
            "$res$people|$time|$location"
        }
    }
    fun encodeToBase64(input: String): String = Base64.getEncoder().encodeToString(input.toByteArray(Charsets.UTF_8))

    fun hmacSHA1(key: String, data: String): ByteArray {
        val hmacSha1 = "HmacSHA1"
        val secretKeySpec = SecretKeySpec(key.toByteArray(Charsets.UTF_8), hmacSha1)
        val mac = Mac.getInstance(hmacSha1)
        mac.init(secretKeySpec)
        return mac.doFinal(data.toByteArray(Charsets.UTF_8))
    }

    fun calculateSignature(policy: String, accessKeySecret: String): String {
        // 将Policy进行base64编码
        val base64Policy = encodeToBase64(policy)
        // 使用AccessKeySecret对待签名的字符串进行签名
        val signatureBytes = hmacSHA1(accessKeySecret, base64Policy)
        // 将签名结果进行base64编码
        return encodeToBase64(String(signatureBytes))
    }
    fun decodeFromBase64(encoded: String): String {
        return String(Base64.getDecoder().decode(encoded), Charsets.UTF_8)
    }
}