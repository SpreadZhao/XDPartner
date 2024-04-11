package com.spread.xdpnetwork.network.interceptor

import com.spread.xdplib.adapter.datamanager.UserManager
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class BaseUrlInterceptor : Interceptor {
    @Volatile
    var newUrl: HttpUrl? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // 检查请求是否有DynamicUrl注解
        request.tag(Invocation::class.java)?.method()?.let { method ->
            method.annotations.forEach { annotation ->
                if (annotation is BaseUrl) {
                    newUrl = UserManager.getInstance().getHost()?.toHttpUrlOrNull()
                    newUrl?.let { newHttpUrl ->
                        // 用新的BaseUrl替换原来的BaseUrl
                        val newUrl = request.url.newBuilder()
                            .scheme(newHttpUrl.scheme)
                            .host(newHttpUrl.host)
                            .port(newHttpUrl.port)
                            .build()
                        request = request.newBuilder().url(newUrl).build()
                    }
                }
            }
        }

        return chain.proceed(request)
    }

    fun setNewUrl(url: String) {
        this.newUrl = url.toHttpUrlOrNull()
    }
}
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl(val value: String)
