package com.github.dhslrl321.mybatch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class TriggerBatchJobController(
    private val jobLauncher: JobLauncher,
    @Qualifier("printLogJob") private val printLogJob: Job,
    @Qualifier("updateUserJob") private val updateUserJob: Job,
) {
    @GetMapping("/batch/print-log")
    fun printLog(): ResponseEntity<String> {
        return try {
            jobLauncher.run(printLogJob, jobParameters())
            ResponseEntity.ok("success")
        } catch (e: Exception) {
            ResponseEntity.ok("failed")
        }
    }

    @GetMapping("/batch/update-user")
    fun updateUser(): ResponseEntity<String> {
        return try {
            jobLauncher.run(updateUserJob, jobParameters())
            ResponseEntity.ok("success")
        } catch (e: Exception) {
            logger.error { e }
            ResponseEntity.ok("failed")
        }
    }

    private fun jobParameters() = JobParameters(
        mapOf(
            "triggeredAt" to JobParameter(
                Instant.now().toEpochMilli().toString(),
                String::class.java
            ),
            "now" to JobParameter(
                Instant.now().toEpochMilli().toString(),
                String::class.java
            )
        )
    )
}
