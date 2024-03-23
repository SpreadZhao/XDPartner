package com.spread.xdplib.adapter.utils

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter

object PageUtil {
    fun gotoActivity(activity: Activity, url:String){
        val postcard = ARouter.getInstance().build(url)

    }

    fun gotoActivityIfExist(activity: Activity, url:String){
        val build = ARouter.getInstance().build(url)
        ARouter.getInstance().build(url).navigation()
    }
}