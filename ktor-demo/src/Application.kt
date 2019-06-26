package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.jetty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.demoModule() {
    install(ContentNegotiation) {
        jackson()
    }
    routing {
        get("/") {
            call.respondText("Hello ${call.parameters["name"]}")
        }

        get("/customers") {
            call.respond(CustomerRepo.getCustomers())
        }
    }
}

data class Customer(val name: String)

object CustomerRepo {
    fun getCustomers() = listOf(Customer("francisco"), Customer("luis"))
}