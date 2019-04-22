package com.meetup.kotlin4all.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.ExpectedCount.once
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.web.client.RestTemplate
import java.net.URI

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [UserService::class, Config::class])
@TestPropertySource(properties = ["jsonplaceholder.apiUrl=http://bananas"])
class UserServiceMockServerTest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var userService: UserService

    lateinit var mockServer: MockRestServiceServer

    private val mapper = ObjectMapper()

    @Before
    fun setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate)
    }

    @Test
    fun `test retrieve all users`() {
        val users = listOf(User(name = "user1"), User(name = "user2"))
        mockServer.expect(once(), requestTo(URI("http://bananas/users")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(users))
                )

        val actUsers = userService.retrieveAllUsers()

        mockServer.verify()
        assertEquals(users, actUsers)
    }
}