package com.github.dhslrl321.mybatch

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MyBatchEntrypoint

fun main(args: Array<String>) {
    runApplication<MyBatchEntrypoint>(*args)
}

val logger = KotlinLogging.logger {}
