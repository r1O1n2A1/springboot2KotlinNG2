package com.mds.springboot2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecureNotesApplication

fun main(args: Array<String>) {
    runApplication<SecureNotesApplication>(*args)
}
