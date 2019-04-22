package com.meetup.kotlin4all.demo

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(HelloWorldController::class)
class HelloWorldControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var itemService: ItemService

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `test hello`() {
        mockMvc.perform(get("/?name=world"))
                .andExpect(status().isOk)
                .andExpect(content().string("Hello world"))
    }

    @Test
    fun `test get items`() {
        whenever(itemService.retrieveAllItems()) doReturn listOf(Item(name = "VW", quantity = 15, price = 2))

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk)
                .andExpect(content().json("[{id:0, name:VW, price:2, quantity:15}]"))
    }

    @Test
    fun `test get users`() {
        whenever(userService.retrieveAllUsers()) doReturn listOf(User(name = "user1"), User(name = "user2"))

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk)
                .andExpect(content().json("""[{"name":"user1"}, {"name":"user2"}]"""))
    }
}
