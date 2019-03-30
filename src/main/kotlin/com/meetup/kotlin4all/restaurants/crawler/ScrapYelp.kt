package com.meetup.kotlin4all.restaurants.crawler

import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class ScrapYelp {

    fun scrap(location: String, start: Int): List<Restaurant> {
        return Jsoup.connect("https://www.yelp.com/search")
            .data("find_loc", location)
            .data("start", start.toString())
            .get().select("h3 a").map {
                Restaurant(it.text(), it.attr("href"))
            }
    }
}

data class Restaurant(val name: String, val path: String)