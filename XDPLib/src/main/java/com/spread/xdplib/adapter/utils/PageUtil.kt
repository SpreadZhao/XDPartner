package com.spread.xdplib.adapter.utils

import android.app.Activity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.spread.xdplib.adapter.constant.ArouterUtil

object PageUtil {
    fun gotoActivity(url:String){
        ARouter.getInstance().build(url).navigation()
    }

    fun gotoActivity(url:String,type:Int){
        ARouter.getInstance().build(url).withInt(ArouterUtil.KEY_SEARCH_TYPE,type).navigation()
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