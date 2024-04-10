package com.spread.xdplib.adapter.utils

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
}