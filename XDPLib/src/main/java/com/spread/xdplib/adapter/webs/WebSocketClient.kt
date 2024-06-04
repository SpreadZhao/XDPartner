package com.spread.xdplib.adapter.webs

import com.spread.xdplib.adapter.utils.TestLogger.logd
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WebSocketClient(private val url: String) {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }
    var session: DefaultClientWebSocketSession? = null

    @OptIn(DelicateCoroutinesApi::class)
    fun connect(listener: WebSocketListener) {
        GlobalScope.launch {
            client.wss(url) {
                listener.onConnected()
                session = this
                sendMessage("{\"command\":1,\"content\":\"hello\",\"token\":\"12345678\"}")
                try {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            listener.onMessage(frame.readText())
                        }
                    }
                } catch (e: Exception) {
                    listener.onDisconnected()
                }
            }
        }
    }

    fun disconnect() {
        client.close()
    }

    private suspend fun sendMessage(mes: String) {
        logd("sendMessage $mes")
        session?.send(Frame.Text(mes))
    }
}