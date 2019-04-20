import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient.newHttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class IntegrationTest {

    @Test
    fun `test hello world`() {
        main()
        val request = HttpRequest.newBuilder().uri(URI("http://localhost:7000?name=meetup.com")).GET()

        val httpResponse = newHttpClient().send(request.build(), HttpResponse.BodyHandlers.ofString())

        assertEquals("Hello meetup.com", httpResponse.body())
    }
}