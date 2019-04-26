package com.example

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.skyscreamer.jsonassert.JSONAssert.assertEquals
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `test hello world`() {
        withTestApplication({ demoModule() }) {
            handleRequest(HttpMethod.Get, "/?name=VW").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello VW", response.content)
            }
        }
    }

    @Test
    fun `test list of customers`() {
        withTestApplication({ demoModule() }) {
            handleRequest(HttpMethod.Get, "/customers").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("[{name:francisco}, {name:luis}]", response.content, false)
            }
        }
    }
}
