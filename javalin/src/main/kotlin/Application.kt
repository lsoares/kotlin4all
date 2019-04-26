import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun main() = runApp(MyLogger())

fun runApp(myLogger: MyLogger) {
    Javalin.create()
        .get("/") { it.result("Hello ${it.queryParam("name")}") }
        .get("/customers") { it.json(CustomerRepo.findAll()) }
        .post("/customers") {
            it.bodyAsClass(Customer::class.java).let { customer ->
                CustomerRepo.save(customer)
                it.status(HttpStatus.CREATED_201)
                myLogger.log("Created $customer")
            }
        }
        .start()
}

data class Customer(val name: String)

object CustomerRepo {
    fun findAll() = listOf(Customer("luis"), Customer("francisco"))
    fun save(customer: Customer) = println(customer)
}

class MyLogger {
    fun log(msg: String) = println(msg)
}