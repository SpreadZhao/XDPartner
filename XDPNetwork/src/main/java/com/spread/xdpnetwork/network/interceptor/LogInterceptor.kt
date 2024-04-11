package com.spread.xdpnetwork.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val UTF8 = Charset.forName("UTF-8")
        // 打印请求报文
        val request: Request = chain.request()
        val requestBody: RequestBody? = request.body
        var reqBody: String? = null
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset = UTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            reqBody = buffer.readString(charset)
        }
        Log.d(TAG, String.format("发送请求 method：%s", request.method))
        Log.d(TAG, String.format( "url：%s",request.url))
        Log.d(TAG, String.format( "headers: %s",request.headers))
        (reqBody)?.let {Log.d(TAG, it)}
        // 打印返回报文
        // 先执行请求，才能够获取报文
        val response: Response = chain.proceed(request)
        val responseBody: ResponseBody? = response.body
        var respBody: String? = null
        if (responseBody != null) {
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            val buffer: Buffer = source.buffer()
            var charset = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8)
                } catch (e: UnsupportedCharsetException) {
                    e.printStackTrace()
                }
            }
            respBody = buffer.clone().readString(charset)
        }

        Log.d(TAG, String.format("收到响应 method：%s %s", request.method,response.message))
        Log.d(TAG, String.format( "url：%s",request.url))
        Log.d(TAG, String.format( "请求body: %s",reqBody))
        Log.d(TAG, String.format( "响应body: %s",respBody))
        return response
    }

    companion object {
        private val TAG = LogInterceptor::class.java.simpleName
    }
}

