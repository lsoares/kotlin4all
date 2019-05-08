# Kotlin4All

## Kotlin reference libraries
* Reactive programming
  * [RxKotlin](https://github.com/ReactiveX/RxKotlin)
  * [Vert.x](https://vertx.io/)
  * [Spring WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)
* Functional programming
  * [Arrow](https://arrow-kt.io/)
* DSLs
  * [kotlinx.html](https://github.com/Kotlin/kotlinx.html)
  * [Exposed](https://github.com/JetBrains/Exposed)
  * [Anko](https://github.com/Kotlin/anko)
  * [Kotlin Gradle](https://kotlinlang.org/docs/reference/using-gradle.html)
* Testing
  * [Spek](https://spekframework.org/)
  * [KotlinTest](https://github.com/kotlintest/kotlintest)
* Other
  * [JKid](https://github.com/yole/jkid)

## From Java to Kotlin
Java | Kotlin
------------ | -------------
`final` modifier | val
`null` test (nested `if`s) and NPEs | Safe call operator (`?.`) / Null types
Fallback when `null` / Quick exit | Elvis operator (`?:`)
Util classes / Static methods | Package level functions and extensions
Singleton pattern implementation | `object`
Functional interfaces | Lambdas
`switch` with `break`s / Multiple `if`/`else` | `when` operator
Getters/setters/hashCode… / Lombok `@Data` | `data` classes
Delegation pattern implementation | Delegators
Builder pattern implementation / Lombok @Builder | Named and default arguments in constructors
Method/constructor overloads | Named and default arguments in methods/constructors
String formatters, concatenations or appending | String templates
Read strings from files or concatenate strings | Multiline strings
Try with resources | `use`

## Learning resources
* [Kotlin reference](https://kotlinlang.org/docs/reference/)
* [Kotlin In Action](https://www.manning.com/books/kotlin-in-action)
* [Kotlin Community](https://kotlinlang.org/community/)
* [Kotlin Playground](https://play.kotlinlang.org)
* [Kotlin by example](https://play.kotlinlang.org/byExample) (Kotlin features)
* [Kotlin Koans](https://play.kotlinlang.org/koans/overview) (exercises)

## Other resources
* [Spring initializr](https://start.spring.io/)
* [Ktor project generator](https://ktor.io/quickstart/generator.html)
* [Kotlin resources](https://www.kotlinresources.com)
