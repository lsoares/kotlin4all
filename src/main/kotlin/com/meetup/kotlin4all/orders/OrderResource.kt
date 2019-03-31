package com.meetup.kotlin4all.orders


import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("orders")
class OrderResource(
    @Qualifier("EatStreet") val provider: OnlineFoodAPI
) {

    @PostMapping("order")
    fun placeOrder(@RequestBody newOrder: NewOrder) : ResponseEntity<Order>  {
        val order = provider.placeOrder(newOrder)
        return ResponseEntity.status(HttpStatus.CREATED).body(order)
    }
}