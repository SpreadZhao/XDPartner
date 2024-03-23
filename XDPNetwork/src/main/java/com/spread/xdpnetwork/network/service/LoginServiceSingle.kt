package com.spread.xdpnetwork.network.service

import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.entry.Blog
import com.spread.xdplib.adapter.utils.MmkvUtil
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

    fun queryHottestBlog(current:Int, callback: ((data:List<Blog>) -> Unit)) {
        service.queryHottestBlog(current).enqueue(object :
            BasicThreadingCallback<BlogsResponse>(
                {
                    if (it.code() == 200) {
                        callback.invoke(it.body()!!.data)
                    }
                }
            ) {})
    }
}