package com.meetup.kotlin4all.demo

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@RunWith(MockitoJUnitRunner::class)
class UserServiceTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    lateinit var userService: UserService

    @Before
    fun setup() {
        userService = UserService("http://bananas", restTemplate)
    }

    @Test
    fun `test retrieve all users`() {
        // given
        val response = ResponseEntity(listOf(User(name = "user1"), User(name = "user2")), HttpStatus.OK)
        val type = object : ParameterizedTypeReference<List<User>>() {}

        whenever(restTemplate.exchange(
                "http://bananas/users",
                HttpMethod.GET,
                null,
                type)) doReturn response

        // when
        val users = userService.retrieveAllUsers()

        // then
        verify(restTemplate).exchange("http://bananas/users",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<User>>() {})
        assertEquals(listOf(User(name = "user1"), User(name = "user2")), users)
    }
}