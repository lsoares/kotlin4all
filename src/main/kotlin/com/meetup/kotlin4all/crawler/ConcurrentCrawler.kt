package com.meetup.kotlin4all.crawler

import com.meetup.kotlin4all.ScrapYelp
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf("lisbon", "porto", "faro", "coimbra").map { loc ->
        1.rangeTo(10).map { page ->
            launch {
                ScrapYelp().scrap(loc, page)
                println("done $page $loc")
            }
        }
    }.flatten().joinAll()
}
