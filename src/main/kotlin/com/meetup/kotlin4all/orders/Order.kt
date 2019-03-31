package com.meetup.kotlin4all.orders

import java.math.BigDecimal

// Order Request
data class NewOrder(
    val restaurantAPIKey: String,
    val Items: List<OrderItem> = emptyList(),
    val method: String,
    val payment: String,
    val test: Boolean = false,
    val card: CreditCard,
    val address: Address,
    val recipient: User
)

data class OrderItem(
    val apiKey: String,
    val name: String,
    val comments: String,
    val basePrice: BigDecimal,
    val totalPrice: BigDecimal,
    val customizationChoices: OrderCustomizationChoice
)

data class OrderCustomizationChoice(
    val apiKey: String,
    val details: String,
    val price: BigDecimal
)

data class CreditCard(
    val apiKey: String,
    val nickName: String,
    val cardholderName: String,
    val cardholderStreetAddress: String,
    val cardholderZip: String,
    val lastFour: String,
    val cvv: String,
    val expirationMonth: Int,
    val expirationYear: String
)

data class Address(
    val apiKey: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val zip: String,
    val aptNumber: String,
    val latitude: String,
    val longitude: String
)

data class User(
    val apiKey: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val savedAddresses: List<Address> = emptyList(),
    val creditCards: List<CreditCard> = emptyList()
)

// An order for food to be delivered or picked up from a restaurant
data class Order(
    val apiKey: String,
    val id: Long,
    val datePlaced: Long,
    val method: String,
    val payment: String,
    val restaurantAPIKey: String,
    val card: CreditCard,
    val address: Address,
    val Items: List<OrderItem> = emptyList()
)