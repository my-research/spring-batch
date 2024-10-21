package com.github.dhslrl321.mybatch

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyBatchEntrypoint

fun main(args: Array<String>) {
  runApplication<MyBatchEntrypoint>(*args)
}

val logger = KotlinLogging.logger {}
