package com.spread.xdpnetwork.network.service

import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.utils.MmkvUtil
import com.spread.xdplib.adapter.utils.TestLogger.log
import com.spread.xdpnetwork.network.BasicThreadingCallback
import com.spread.xdpnetwork.network.model.response.BlogsResponse
import com.spread.xdpnetwork.network.model.response.TestLoginResponse

class LoginServiceSingle private constructor() {
    private var service: LoginService = ServiceCreator.create(LoginService::class.java)

    companion object {
        val instance: LoginServiceSingle by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoginServiceSingle()
        }
    }

    fun testLogin(stuId: String, password: String, callback: (() -> Unit)) {
        service.testLogin(stuId, password).enqueue(object :
            BasicThreadingCallback<TestLoginResponse>(
                {
                    if (it.code() == 200) {
                        MmkvUtil.put(MmkvConstant.MMKV_KEY_TOKEN, it.body()!!.data.token)
                        MmkvUtil.put(MmkvConstant.MMKV_KEY_USERID, it.body()!!.data.userId)
                        callback.invoke()
                    }
                }
            ) {})
    }

    fun queryBlogByPosition(position:Int, current:Int, callback: ((data:List<Blog>) -> Unit)) {
        log("position ${position.toString()}")
        if(position == 2){
            service.queryHottestBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        } else if(position == 1){
            service.queryLikeBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        } else if(position == 0){
            service.queryNewestBlog(current).enqueue(object :
                BasicThreadingCallback<BlogsResponse>(
                    {
                        if (it.code() == 200) {
                            callback.invoke(it.body()!!.data)
                        }
                    }
                ) {})
        }

    }
}