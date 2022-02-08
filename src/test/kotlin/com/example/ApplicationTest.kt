package com.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.websocket.WebSockets
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.transport.ktor.client.RSocketSupport
import io.rsocket.kotlin.transport.ktor.client.rSocket
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun testRequestStream() {
        runBlocking {
            val client = HttpClient(OkHttp) {
                install(WebSockets)
                install(RSocketSupport)
            }
            val stream = client
                .rSocket("ws://localhost:8080/stream")
                .requestStream(Payload.Empty)
            stream
                .take(2)
                .toList()
            client.close()
        }
    }
}