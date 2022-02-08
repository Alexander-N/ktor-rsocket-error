package com.example

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.routing.routing
import io.ktor.websocket.WebSockets
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.core.RSocketServer
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import io.rsocket.kotlin.transport.ktor.server.RSocketSupport
import io.rsocket.kotlin.transport.ktor.server.rSocket
import kotlinx.coroutines.flow.flow


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(WebSockets)
    install(RSocketSupport) {
        server = RSocketServer()
    }

    routing {
        rSocket("stream") {
            RSocketRequestHandler {
                log.info("New subscriber")
                requestStream {
                    flow {
                        repeat(2) { i ->
                            log.info("Emit $i")
                            emit(buildPayload { data("data: $i") })
                        }
                    }
                }
            }
        }
    }
}
