package com.meetup.kotlin4all.restaurants.crawler

import com.meetup.kotlin4all.restaurants.Restaurant
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class ScrapYelp {

    fun scrap(location: String, start: Int): List<Restaurant> {
        return Jsoup
            .connect("https://www.yelp.com/search")
            .data("find_loc", location)
            .data("start", start.toString())
            .get()
            .select("h3 a")
            .map {
                Restaurant(name = it.text(), path = it.attr("href"))
            }
    }

    fun scrap(location: String, start: Int, restaurantDoneChannel: Channel<Restaurant>) {
        Jsoup
            .connect("https://www.yelp.com/search")
            .data("find_loc", location)
            .data("start", start.toString())
            .get()
            .select("h3 a")
            .map {
                GlobalScope.launch {
                    println("will send: " + Restaurant(name = it.text(), path = it.attr("href")))
                    restaurantDoneChannel.send(Restaurant(name = it.text(), path = it.attr("href")))
                }
            }
    }
}
