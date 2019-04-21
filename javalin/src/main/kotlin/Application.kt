import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun main() {
    Javalin.create()
        .get("/") { it.result("Hello ${it.queryParam("name")}") }
        .get("/customers") { it.json(CustomerRepo.findAll()) }
        .post("/customers") {
            CustomerRepo.save(           it.bodyAsClass(Customer::class.java))
            it.status(HttpStatus.CREATED_201)
        }
        .start()
}

data class Customer(val name: String)
object CustomerRepo {
    fun findAll() = listOf(Customer("luis"), Customer("francisco"))
    fun save(customer: Customer) {
        println(customer)
    }
}