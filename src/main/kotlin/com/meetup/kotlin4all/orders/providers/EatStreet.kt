package com.meetup.kotlin4all.orders.providers

import com.meetup.kotlin4all.exceptions.InvalidOrderException
import com.meetup.kotlin4all.orders.NewOrder
import com.meetup.kotlin4all.orders.OnlineFoodAPI
import com.meetup.kotlin4all.orders.Order
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class EatStreet(val restTemplate: RestTemplate,
                @Value("\${eat-street.apiUrl}") val apiUrl: String,
                @Value("\${eat-street.apiAuthKey}") val apiAuthKey: String) : OnlineFoodAPI {
    override fun placeOrder(newOrder: NewOrder): Order {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            set("X-Access-Token", apiAuthKey)
        }

        val request = HttpEntity(newOrder, headers)
        val response = restTemplate
            .exchange("$apiUrl/publicapi/v1/send-order", HttpMethod.POST, request, Order::class.java)

        return response.body ?: throw InvalidOrderException()
    }
}