package com.spread.xdplib.adapter.constant

import android.util.SparseArray
import com.spread.xdplib.adapter.utils.TestLogger.logd

object MapUtil {

    private val constellationMap: SparseArray<String> by lazy(LazyThreadSafetyMode.NONE) {
        SparseArray<String>(13).apply {
            append(1, "白羊座")
            append(2, "金牛座")
            append(3, "双子座")
            append(4, "巨蟹座")
            append(5, "狮子座")
            append(6, "处女座")
            append(7, "天秤座")
            append(8, "天蝎座")
            append(9, "射手座")
            append(10, "摩羯座")
            append(11, "水瓶座")
            append(12, "双鱼座")
        }
    }
    private val mbtiMap: SparseArray<String> by lazy(LazyThreadSafetyMode.NONE) {
        SparseArray<String>(17).apply {
            append(1, "ISTJ, 内倾感觉型思考者")
            append(2, "ISFJ, 内倾感觉型情感者")
            append(3, "INFJ, 内倾直觉型情感者")
            append(4, "INTJ, 内倾直觉型思考者")
            append(5, "ISTP, 内倾感觉型思考型")
            append(6, "ISFP, 内倾感觉型情感型")
            append(7, "INFP, 内倾直觉型情感型")
            append(8, "INTP, 内倾直觉型思考型")
            append(9, "ESTP, 外倾感觉型思考型")
            append(10, "ESFP, 外倾感觉型情感型")
            append(11, "ENFP, 外倾直觉型情感型")
            append(12, "ENTP, 外倾直觉型思考型")
            append(13, "ESTJ, 外倾感觉型思考者")
            append(14, "ESFJ, 外倾感觉型情感者")
            append(15, "ENFJ, 外倾直觉型情感者")
            append(16, "ENTJ, 外倾直觉型思考者")

        }
    }

    private val typeMap:SparseArray<String> by lazy(LazyThreadSafetyMode.NONE) {
        SparseArray<String>(5).apply {
            append(1,"学习")
            append(2,"娱乐")
            append(3,"恋爱")
            append(4,"生活")
        }
    }
    private val demandMap:SparseArray<String> by lazy(LazyThreadSafetyMode.NONE) {
        SparseArray<String>(4).apply {
            append(1,"竞赛")
            append(2,"脱单")
            append(3,"出游")
            append(4,"未知")
        }
    }
    fun getConstellationName(key: Int): String {
        if(key == 0) return "未设置星座"
        return constellationMap.get(key)
    }

    fun getMbtiName(key: Int): String {
        if(key == 0) return "未设置mbti"
        return mbtiMap.get(key)
    }

    fun getTypeName(key: Int): String {
        return typeMap.get(key)
    }

    fun getDemandName(key: Int): String {
        if(key == 0) return "未设置需求"
        return demandMap.get(key)
    }


}