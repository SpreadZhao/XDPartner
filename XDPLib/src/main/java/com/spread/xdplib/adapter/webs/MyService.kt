package com.spread.xdplib.adapter.webs

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.spread.xdplib.adapter.utils.TestLogger.logd


class MyService : Service(),WebSocketListener {
    private val webSocketClient = WebSocketClient("wss://xdu-partner-ws.be.wizzstudio.com")
    inner class MyBinder : Binder() {
        fun connect() {
            logd("MyService connect")
            webSocketClient.connect(this@MyService)
        }

        fun disConnect() {

        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun onDestory() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder {
        //第一步执行onBind方法
        return MyBinder()
    }

    override fun onConnected() {
        logd("onConnected ")
    }

    override fun onMessage(message: String) {
        logd("onMessage $message")
    }

    override fun onDisconnected() {
        logd("onConnected")
    }
}
interface WebSocketListener {
    fun onConnected()
    fun onMessage(message: String)
    fun onDisconnected()
}