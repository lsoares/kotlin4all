package com.example

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
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