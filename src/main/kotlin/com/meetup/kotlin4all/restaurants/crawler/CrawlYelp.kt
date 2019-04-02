package com.meetup.kotlin4all.restaurants.crawler

import com.meetup.kotlin4all.restaurants.RestaurantsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class CrawlYelp(val scrapYelp: ScrapYelp, val restaurantsRepository: RestaurantsRepository) {
    val cities = listOf("lisbon", "porto", "faro", "coimbra")

    fun crawl() {
        cities.forEach { loc ->
            for (start in 1..10) {
                val rests = scrapYelp.scrap(loc, start * 10)
                restaurantsRepository.saveAll(rests)
            }
        }
    }

    fun concurrentCrawl(): List<Job> {
        return cities.map { loc ->
            1.rangeTo(10).map { start ->
                GlobalScope.launch {
                    val rests = scrapYelp.scrap(loc, start * 10)
                    restaurantsRepository.saveAll(rests)
                }
            }
        }.flatten()
    }
}
