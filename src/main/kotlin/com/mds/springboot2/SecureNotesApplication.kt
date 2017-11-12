package com.mds.springboot2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class SecureNotesApplication

fun main(args: Array<String>) {
    runApplication<SecureNotesApplication>(*args)
}
