package com.github.dhslrl321.mybatch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableBatchProcessing
class BatchConfiguration(
    private val jobRepository: JobRepository,
) {
    /*@Bean
    fun jobRunner(jobLauncher: JobLauncher, job: Job): ApplicationRunner {
        return ApplicationRunner {
            val jobParameters = JobParameters(mapOf("a" to JobParameter("hello", String::class.java)))
            val execution = jobLauncher.run(job, jobParameters)
            println("Job Execution Status: ${execution.status}")
        }
    }*/

    @Bean
    @Throws(Exception::class)
    fun jobLauncher(): JobLauncher {
        val jobLauncher = TaskExecutorJobLauncher()
        jobLauncher.setJobRepository(jobRepository)
        jobLauncher.afterPropertiesSet()
        return jobLauncher
    }
}
