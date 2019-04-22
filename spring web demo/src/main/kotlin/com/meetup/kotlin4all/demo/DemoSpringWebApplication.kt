package com.meetup.kotlin4all.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
@RequestMapping("/")
class HelloWorldController(private val itemService: ItemService, private val userService: UserService) {

    @GetMapping
    fun sayHello(@RequestParam name: String) = "Hello $name"

    @GetMapping("items")
    fun getItems() = itemService.retrieveAllItems()

    @GetMapping("users")
    fun getUsers() = userService.retrieveAllUsers()
}

@Service
class ItemService(private val itemRepository: ItemRepository) {
    fun retrieveAllItems(): List<Item> = itemRepository.findAll()
}

@Service
class UserService(@Value("\${jsonplaceholder.apiUrl}") val apiUrl: String,
                  private val restTemplate: RestTemplate) {

    fun retrieveAllUsers(): List<User> {
        val response = restTemplate.exchange(
                "$apiUrl/users",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<User>>() {})
        return response.body ?: emptyList()
    }
}

@Repository
interface ItemRepository : JpaRepository<Item, Int>

@Entity
data class Item(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0,
        val name: String = "",
        val price: Int = 0,
        val quantity: Int = 0
)

data class User(
        val id: Int = 0,
        val name: String = "",
        val username: String = "",
        val email: String = ""
)

@Configuration
class Config {
    @Bean
    fun restTemplate() = RestTemplate()
}

