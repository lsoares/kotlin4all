package com.meetup.kotlin4all.orders

interface OnlineFoodAPI {
    fun placeOrder(newOrder: NewOrder): Order
}