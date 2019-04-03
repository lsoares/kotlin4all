package com.meetup.kotlin4all.restaurants

import com.meetup.kotlin4all.restaurants.crawler.CrawlYelp
import com.meetup.kotlin4all.restaurants.crawler.ScrapYelp
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("restaurants")
class RestaurantResource(
    val restaurantsRepository: RestaurantsRepository,
    val scrapYelp: ScrapYelp,
    val crawlYelp: CrawlYelp
) {

    @GetMapping("scrap")
    fun scrap(@RequestParam loc: String, @RequestParam start: Int) =
        scrapYelp.scrap(loc, start)

    @PostMapping("crawl")
    fun crawl(@RequestParam(defaultValue = "true") concurrent: Boolean): String {
        if (concurrent)
            crawlYelp.concurrentCrawlWithChannels()
        else
            crawlYelp.crawl()

        return "crawling"
    }

    @GetMapping
    fun listRestaurants() =
        restaurantsRepository.findAll()
}