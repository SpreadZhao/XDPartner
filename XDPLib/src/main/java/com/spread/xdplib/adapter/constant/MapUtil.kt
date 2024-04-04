package com.spread.xdplib.adapter.constant

import android.util.SparseArray

object MapUtil {

    private val constellationMap:SparseArray<String> = SparseArray<String>(12).apply {
        append(0,"1")
        append(1,"1")
        append(2,"1")
        append(3,"1")
        append(4,"1")
        append(5,"1")
        append(6,"1")
        append(7,"1")
        append(8,"1")
        append(9,"1")
        append(10,"1")
        append(11,"1")
    }
    fun getConstellationName(key:Int):String{
        return constellationMap.get(key)
    }
}