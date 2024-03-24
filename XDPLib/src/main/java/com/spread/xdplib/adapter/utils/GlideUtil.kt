package com.spread.xdplib.adapter.utils

import android.content.Context
import com.bumptech.glide.Glide

object GlideUtil {

    fun isResumeRequests(context:Context,isResume : Boolean){
        if(isResume){
            if (Glide.with(context).isPaused) {
                Glide.with(context).resumeRequests()
            }
        } else {
            if (!Glide.with(context).isPaused) {
                Glide.with(context).pauseRequests()
            }
        }
    }

}