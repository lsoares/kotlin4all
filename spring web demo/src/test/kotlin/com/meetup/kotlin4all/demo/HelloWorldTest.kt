package com.meetup.kotlin4all.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(HelloWorldController::class) // only boots with this controller
class HelloWorldTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test hello`() {
        mockMvc.perform(get("/?name=world"))
                .andExpect(status().isOk)
                .andExpect(content().string("Hello world"))
    }
}
