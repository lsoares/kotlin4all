package com.meetup.kotlin4all.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
class HelloWorldController(private val itemService: ItemService) {

    @GetMapping
    fun sayHello(@RequestParam name: String) = "Hello $name"

    @GetMapping("items")
    fun getItems() = itemService.retrieveAllItems()
}

@Service
class ItemService(private val itemRepository: ItemRepository) {
    fun retrieveAllItems(): List<Item> = itemRepository.findAll()
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

