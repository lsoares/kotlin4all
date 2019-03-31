package com.meetup.kotlin4all.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class InvalidOrderException(msg: String = "Was not possible to create the Order.") : RuntimeException(msg)

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(InvalidOrderException::class)
    fun invalidOrderException(exception: InvalidOrderException) =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(exception.message)
}