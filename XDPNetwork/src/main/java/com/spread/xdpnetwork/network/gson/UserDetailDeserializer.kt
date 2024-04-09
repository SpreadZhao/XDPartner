package com.spread.xdpnetwork.network.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.spread.xdplib.adapter.entry.UserDetail
import java.lang.reflect.Type

class UserDetailDeserializer : JsonDeserializer<UserDetail> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): UserDetail {
        val jsonObject = json.asJsonObject
        return UserDetail(
            constellation =  if (jsonObject.has("constellation")) jsonObject.get("constellation").asInt else 0, // 需要处理可能的缺失或类型不匹配问题
            highTag = if (jsonObject.has("highTag")) jsonObject.get("highTag").asInt else 0,
            icon = jsonObject.get("icon").asString,
            majorName = if (jsonObject.has("majorName")) jsonObject.get("majorName").asString else "未设置",
            mbti = if (jsonObject.has("mbti")) jsonObject.get("mbti").asInt else 0,
            myDescription = jsonObject.get("myDescription").asString,
            nickName = jsonObject.get("nickName").asString,
            picture = context.deserialize(jsonObject.get("picture"), object : TypeToken<List<String>>() {}.type) ?: emptyList(),
            qq = if (jsonObject.has("qq")) jsonObject.get("qq").asString else "未设置"
        )
    }
}
