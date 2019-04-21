import io.mockk.*
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient.newHttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.*
import java.net.http.HttpResponse.*

class IntegrationTest {

    @Test
    fun `test hello world`() {
        main()
        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:7000?name=meetup.com"))
            .GET()

        val httpResponse = newHttpClient().send(request.build(), BodyHandlers.ofString())

        assertEquals("Hello meetup.com", httpResponse.body())
        assertEquals(HttpStatus.OK_200, httpResponse.statusCode())
    }

    @Test
    fun `test create customer`() {
        mockkObject(CustomerRepo)
        every { CustomerRepo.save(Customer("test")) } just Runs

        main()
        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:7000/customers"))
            .header("Content-Type", "application/json")
            .POST(ofString("{\"name\":\"test\"}"))

        val httpResponse = newHttpClient().send(request.build(), BodyHandlers.ofString())

        verify { CustomerRepo.save(Customer("test")) }
        assertEquals("", httpResponse.body())
        assertEquals(HttpStatus.CREATED_201, httpResponse.statusCode())
    }
}
