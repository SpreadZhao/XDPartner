package com.spread.xdplib.adapter.datamanager


import com.google.gson.Gson
import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdplib.adapter.utils.MmkvUtil

class UserManager private constructor(){
    companion object : SingleHolder<UserManager>(::UserManager)


    fun saveUserDetail(userDetail: UserDetail){
        MmkvUtil.put(MmkvConstant.MMKV_KEY_USER_DETAIL, Gson().toJson(userDetail))
    }

    fun getUserDetail(): UserDetail? {
        return Gson().fromJson(
            MmkvUtil.getString(MmkvConstant.MMKV_KEY_USER_DETAIL),
            UserDetail::class.java
        )
    }

    fun saveToken(token:String){
        MmkvUtil.put(MmkvConstant.MMKV_KEY_TOKEN,token)
    }

    fun saveUserId(userId:Int){
        MmkvUtil.put(MmkvConstant.MMKV_KEY_USERID,userId)
    }

    fun saveHost(host:String){
        MmkvUtil.put(MmkvConstant.MMKV_KEY_HOST,host)
    }
    fun getUserId(): Int {
        return MmkvUtil.getInt(MmkvConstant.MMKV_KEY_USERID)
    }
    fun getHost(): String? {
        return MmkvUtil.getString(MmkvConstant.MMKV_KEY_HOST)
    }
}
