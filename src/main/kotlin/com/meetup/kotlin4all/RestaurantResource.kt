package com.meetup.kotlin4all

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RestaurantResource(val scrapYelp: ScrapYelp) {

    @GetMapping("restaurants")
    fun getRestaurants() = scrapYelp.scrap("porto", 1)
}