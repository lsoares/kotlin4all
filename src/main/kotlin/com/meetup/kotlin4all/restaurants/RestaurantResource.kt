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
    fun crawl() {
        crawlYelp.concurrentCrawl()
    }

    @GetMapping
    fun listRestaurants() =
        restaurantsRepository.findAll()
}