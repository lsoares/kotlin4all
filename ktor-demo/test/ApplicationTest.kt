package com.example

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.skyscreamer.jsonassert.JSONAssert

class ApplicationTest {

    @Test
    fun `test hello world`() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/?name=VW").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello VW", response.content)
            }
        }
    }

    @Test
    fun `test list of customers`() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/customers").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("[{name:francisco}, {name:luis}]", response.content, false)
            }
        }
    }
}
