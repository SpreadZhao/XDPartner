package com.spread.xdplib.adapter.utils

import android.app.Activity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

object PageUtil {
    fun gotoActivity(url:String){
        ARouter.getInstance().build(url).navigation()
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