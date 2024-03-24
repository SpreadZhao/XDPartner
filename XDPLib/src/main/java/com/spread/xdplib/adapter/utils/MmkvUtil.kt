package com.spread.xdplib.adapter.utils

import com.tencent.mmkv.MMKV

object MmkvUtil {

    private val mmkv = MMKV.defaultMMKV()

    fun put(key:String,value:String){
        mmkv.putString(key,value)
    }

    fun put(key:String,value:Int){
        mmkv.putInt(key,value)
    }

    fun getString(key: String):String?{
        return mmkv.getString(key,null)
    }

    fun getString(key: String,def :String):String?{
        return mmkv.getString(key,def)
    }

    fun getInt(key: String):Int{
        return mmkv.getInt(key,0)
    }

    fun getInt(key: String,def:Int):Int{
        return mmkv.getInt(key,def)
    }
}