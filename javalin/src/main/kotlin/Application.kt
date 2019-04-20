import io.javalin.Javalin

fun main() {
    Javalin.create()
        .get("/") { it.result("Hello ${it.queryParam("name")}")}
        .get("/customers") { it.json(CustomerRepo.findAll()) }
        .start()
}

data class Customer(val name: String)
object CustomerRepo {
    fun findAll() = listOf(Customer("luis"), Customer("francisco"))
}