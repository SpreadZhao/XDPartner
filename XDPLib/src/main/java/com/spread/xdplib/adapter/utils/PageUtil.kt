package com.spread.xdplib.adapter.utils

import android.app.Activity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdplib.adapter.constant.ArouterUtil
import com.spread.xdplib.adapter.entry.UserDetail
import com.spread.xdplib.adapter.entry.UserVo

object PageUtil {
    fun gotoActivity(url:String){
        ARouter.getInstance().build(url).navigation()
    }

    fun gotoActivityWithType(url:String, type:Int){
        ARouter.getInstance().build(url).withInt(ArouterUtil.KEY_SEARCH_TYPE,type).navigation()
    }
    fun gotoActivityWithUserId(url:String, keyUserId:Long){
        ARouter.getInstance().build(url).withLong(ArouterUtil.KEY_USERID,keyUserId).navigation()
    }
    fun gotoActivityWithUserIdAndUserDetail(url:String, keyUserId:Long,userDetail: UserDetail){
        ARouter.getInstance().build(url)
            .withLong(ArouterUtil.KEY_USERID,keyUserId)
            .withParcelable(ArouterUtil.KEY_USER_DETAIL,userDetail).navigation()
    }
    fun gotoActivityWithUserVo(url:String, keyUserVo:UserVo){
        ARouter.getInstance().build(url).withParcelable(ArouterUtil.KEY_USER_VO,keyUserVo).navigation()
    }
    fun gotoActivityIfExist(activity: Activity, url:String){
        ARouter.getInstance().build(url).navigation(activity,object : NavigationCallback{
            override fun onFound(postcard: Postcard?) {
            }

            override fun onLost(postcard: Postcard?) {
            }

            override fun onArrival(postcard: Postcard?) {
                activity.finish()
            }

            override fun onInterrupt(postcard: Postcard?) {
            }

        })
    }

}