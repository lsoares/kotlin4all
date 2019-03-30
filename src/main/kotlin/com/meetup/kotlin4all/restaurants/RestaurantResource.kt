package com.meetup.kotlin4all.restaurants

import com.meetup.kotlin4all.restaurants.crawler.ScrapYelp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RestaurantResource(val scrapYelp: ScrapYelp) {

    @GetMapping("restaurants")
    fun getRestaurants() = scrapYelp.scrap("porto", 1)
}