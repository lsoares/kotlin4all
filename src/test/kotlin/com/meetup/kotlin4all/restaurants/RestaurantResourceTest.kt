package com.meetup.kotlin4all.restaurants

import com.meetup.kotlin4all.restaurants.crawler.CrawlYelp
import com.meetup.kotlin4all.restaurants.crawler.ScrapYelp
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
@WebMvcTest(RestaurantResource::class)
class RestaurantResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var restaurantsRepository: RestaurantsRepository

    @MockBean
    private lateinit var scrapYelp: ScrapYelp

    @MockBean
    private lateinit var crawlYelp: CrawlYelp

    @Test
    fun testFindAll() {
        whenever(restaurantsRepository.findAll())
            .thenReturn(listOf(Restaurant(name = "a", path = "1"), Restaurant(name = "b", path = "2")))

        mockMvc.perform(get("/restaurants"))
            .andExpect(status().isOk)
            .andExpect(content().json("[{name: a, path: \"1\"}, {name: b, path: \"2\"}]"))
    }
}