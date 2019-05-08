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
* Web
  * [Ktor](https://ktor.io/)
  * [JKid](https://github.com/yole/jkid)

## From Java to Kotlin
Java | Kotlin
------------ | -------------
`Integer` / `int` | `Int`
Explicit types | Inferred types
varargs (`...`) | `varargs` modifier
`extends` | `:`
`implements` | `:`
Optional `@Override` | mandatory `override` 
`final` modifier | val
`null` test (nested `if`s) and NPEs | Safe call operator (`?.`) / Null types
`@Nullable`, `@NotNull` and similar annotations | Not nullable types
`unmodifiableCollection` / Guava collection factories (e.g. `ImmutableMap.of`) | read only factory factions
Verbose collections initializations / `Map.of` | Collections factory functions (e.g. `mapOf` / `listOf` / `setOf`)
`map.get(key)` / `map.set(key,val)` | `map\[key\]` / `map\[key\]=val`
Apache Range class | Native ranges
Ternary operator | `if` as an expression
`switch` with `break`s / multiple `if`/`else` | `when` operator
Fallback when `null` / Quick return | Elvis operator (`?:`)
Lombok `@Data` | `data` classes
Getters and setters | Properties
Util. classes / `static` functions | Package level functions / Extensions
Singleton pattern implementation | `object`
Delegation pattern implementation | `by`
Builder pattern implementation / Lombok `@Builder` | Named and default arguments in constructors / `apply`
Functional interfaces | Lambdas
Method/constructor overloads | Named and default arguments in methods/constructors
String formatting/concatenations/appending | String templates
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
