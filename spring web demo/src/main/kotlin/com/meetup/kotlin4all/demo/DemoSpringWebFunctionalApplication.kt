package com.meetup.kotlin4all.demo

import io.javalin.Javalin

fun main(args: Array<String>) {
    Javalin.create().start()
            .get("/") { it.result("Hello ${it.queryParam("name")}") }
}