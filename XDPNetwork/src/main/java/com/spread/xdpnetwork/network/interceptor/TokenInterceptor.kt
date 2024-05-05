package com.spread.xdpnetwork.network.interceptor

import com.spread.xdplib.adapter.constant.MmkvConstant
import com.spread.xdplib.adapter.utils.MmkvUtil
import com.spread.xdplib.adapter.utils.TestLogger.logd
import com.spread.xdpnetwork.network.NetworkConstant
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = MmkvUtil.getString(MmkvConstant.MMKV_KEY_TOKEN)

        // 如果Token存在，将其添加到请求头中
        val request = if (token == null) {
            logd("token is null")
            original.newBuilder()
                .header("token", NetworkConstant.mToken)
                .method(original.method, original.body)
                .build()
        } else {
            original
        }

        return chain.proceed(request)
    }
}