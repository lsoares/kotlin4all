package com.meetup.kotlin4all.restaurants

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Repository
interface RestaurantsRepository : PagingAndSortingRepository<Restaurant, Int>

@Entity
data class Restaurant(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String = "",
    val path: String = ""
)