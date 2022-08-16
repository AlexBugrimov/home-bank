package dev.bug.homebank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomeBankApplication

fun main(args: Array<String>) {
    runApplication<HomeBankApplication>(*args)
}
