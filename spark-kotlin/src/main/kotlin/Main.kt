import spark.kotlin.ignite

fun main() = ignite()
    .get("/") { "Hello ${request.queryParams("name")}" }