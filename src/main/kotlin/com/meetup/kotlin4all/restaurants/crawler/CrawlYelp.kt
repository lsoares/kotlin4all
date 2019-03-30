package com.meetup.kotlin4all.restaurants.crawler

import com.meetup.kotlin4all.restaurants.RestaurantsRepository
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class CrawlYelp(val scrapYelp: ScrapYelp, val restaurantsRepository: RestaurantsRepository) {
    val cities = listOf("lisbon", "porto", "faro", "coimbra")

    fun crawl() {
        cities.forEach { loc ->
            for (page in 1..10) {
                val rests = scrapYelp.scrap(loc, page)
                restaurantsRepository.saveAll(rests)
                println("done $loc $page")
            }
        }
    }

    fun concurrentCrawl() {
        runBlocking {
            cities.map { loc ->
                1.rangeTo(10).map { page ->
                    launch {
                        val rests = scrapYelp.scrap(loc, page)
                        restaurantsRepository.saveAll(rests)
                        println("done $loc $page")
                    }
                }
            }.flatten().joinAll()
        }
    }
}