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
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
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
class HelloWorldController(private val itemService: ItemService, private val userService: UserService, private val postService: PostService) {

    @GetMapping
    fun sayHello(@RequestParam name: String) = "Hello $name"

    @GetMapping("items")
    fun getItems() = itemService.retrieveAllItems()

    @GetMapping("users")
    fun getUsers() = userService.retrieveAllUsers()

    @GetMapping("posts")
    fun getPosts() = postService.retrieveAllPosts()
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

@Service
class PostService(private val postAPI: PostAPI) {
    fun retrieveAllPosts(): List<Post> = postAPI.getAllPosts().execute().body() ?: emptyList()
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

data class Post(
        val userId: Int = 0,
        val id: Int = 0,
        val title: String = "",
        val body: String = ""
)

@Configuration
class Config {
    @Bean
    fun restTemplate() = RestTemplate()
}

@Configuration
class ApisConfiguration {

    @Value("\${jsonplaceholder.apiUrl}")
    private lateinit var apiUrl: String

    @Bean
    fun postApi(): PostAPI {
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(PostAPI::class.java)
    }
}

interface PostAPI {

    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>

}