package ru.kuksov.computers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ComputersApplication

fun main(args: Array<String>) {
	runApplication<ComputersApplication>(*args)
}
