package com.meetup.kotlin4all.crawler

import com.meetup.kotlin4all.ScrapYelp

fun main() {
    listOf("lisbon", "porto", "faro", "coimbra").forEach { location ->
        for (page in 1..10) {
            ScrapYelp().scrap(location, page)
            println("done $page $location")
        }
    }
}
