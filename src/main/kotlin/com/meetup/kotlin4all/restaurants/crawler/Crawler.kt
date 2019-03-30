package com.meetup.kotlin4all.restaurants.crawler

fun main() {
    listOf("lisbon", "porto", "faro", "coimbra").forEach { loc ->
        for (page in 1..10) {
            ScrapYelp().scrap(loc, page)
            println("done $page $loc")
        }
    }
}
