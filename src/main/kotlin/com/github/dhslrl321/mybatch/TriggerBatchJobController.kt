package com.github.dhslrl321.mybatch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TriggerBatchJobController(
    private val jobLauncher: JobLauncher,
    private val job: Job
) {
    @GetMapping("/batch/print-log")
    fun printLog(): ResponseEntity<String> {
        return try {
            jobLauncher.run(job, JobParameters())
            ResponseEntity.ok("success")
        } catch (e: Exception) {
            ResponseEntity.ok("failed")
        }
    }
}
