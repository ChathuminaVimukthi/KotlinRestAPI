package com.hms.simplebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SomeTestApplication

fun main(args: Array<String>) {
    runApplication<SomeTestApplication>(*args)
}

@RestController
class MainController{
    @GetMapping
    fun index() = "Hello World"
}
