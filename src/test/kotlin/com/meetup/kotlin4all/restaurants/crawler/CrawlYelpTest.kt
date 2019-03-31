package com.meetup.kotlin4all.restaurants.crawler

import com.meetup.kotlin4all.restaurants.Restaurant
import com.meetup.kotlin4all.restaurants.RestaurantsRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class CrawlYelpTest {

    @Test
    fun testCrawl() {
        val restaurants = listOf(Restaurant(name = "name", path = "path"))
        val scrapYelp = mock<ScrapYelp> {
            on { scrap(eq("lisbon"), any()) } doReturn restaurants
            on { scrap(eq("porto"), any()) } doReturn restaurants
            on { scrap(eq("faro"), any()) } doReturn restaurants
            on { scrap(eq("coimbra"), any()) } doReturn restaurants
        }
        val restaurantsRepository = mock<RestaurantsRepository>()
        val crawlYelp = CrawlYelp(scrapYelp, restaurantsRepository)

        crawlYelp.crawl()

        verify(restaurantsRepository, times(40)).saveAll(restaurants)
        verify(scrapYelp, times(40)).scrap(any(), any())
    }

    @Test
    fun testConcurrentCrawl() {
        val restaurants = listOf(Restaurant(name = "name", path = "path"))
        val scrapYelp = mock<ScrapYelp> {
            on { scrap(eq("lisbon"), any()) } doReturn restaurants
            on { scrap(eq("porto"), any()) } doReturn restaurants
            on { scrap(eq("faro"), any()) } doReturn restaurants
            on { scrap(eq("coimbra"), any()) } doReturn restaurants
        }
        val restaurantsRepository = mock<RestaurantsRepository>()
        val crawlYelp = CrawlYelp(scrapYelp, restaurantsRepository)

        crawlYelp.concurrentCrawl()

        verify(restaurantsRepository, times(40)).saveAll(restaurants)
        verify(scrapYelp, times(10)).scrap(eq("lisbon"), any())
        verify(scrapYelp, times(10)).scrap(eq("porto"), any())
        verify(scrapYelp, times(10)).scrap(eq("faro"), any())
        verify(scrapYelp, times(10)).scrap(eq("coimbra"), any())
    }
}