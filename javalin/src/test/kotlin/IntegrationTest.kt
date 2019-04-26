
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient.newHttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.ofString
import java.net.http.HttpResponse.BodyHandlers.ofString

class IntegrationTest {

    @Test
    fun `test hello world`() {
        main()
        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:7000?name=meetup.com"))
            .GET()

        val response = newHttpClient().send(request.build(), ofString())

        assertEquals("Hello meetup.com", response.body())
        assertEquals(200, response.statusCode())
    }

    @Test
    fun `test create customer`() {
        mockkObject(CustomerRepo)
        every { CustomerRepo.save(Customer("test")) } just Runs
        val myLogger = mockk<MyLogger> {
            every { log(any()) } just Runs
        }
        runApp(myLogger)
        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:7000/customers"))
            .header("Content-Type", "application/json")
            .POST(ofString("{\"name\":\"test\"}"))

        val response = newHttpClient().send(request.build(), ofString())

        verify { CustomerRepo.save(Customer("test")) }
        verify { myLogger.log("Created Customer(name=test)") }
        assertEquals("", response.body())
        assertEquals(201, response.statusCode())
    }
}
