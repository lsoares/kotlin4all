package com.meetup.kotlin4all.restaurants.crawler

import com.meetup.kotlin4all.restaurants.Restaurant
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class ScrapYelp {

    fun scrap(location: String, start: Int): List<Restaurant> {
        return Jsoup.connect("https://www.yelp.com/search")
            .data("find_loc", location)
            .data("start", start.toString())
            .get().select("h3 a").map {
                Restaurant(name = it.text(), path = it.attr("href"))
            }
    }
}
