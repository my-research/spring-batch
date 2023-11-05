package com.github.dhslrl321.mybatch

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class UserUpdateJobConfiguration {


    companion object {
        private const val JOB_NAME = "UPDATE_USER_JOB"
        private const val STEP_NAME = "UPDATE_USER_STEP"
    }

    @Bean
    fun updateUserJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(updateUserStep(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun updateUserStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder(STEP_NAME, jobRepository)
            .tasklet(updateUserTasklet(), transactionManager)
            .build()
    }

    @Bean
    fun updateUserTasklet(): Tasklet {
        return Tasklet { _, _ ->
            println("Hello, Spring Batch!! updating user")
            null
        }
    }
}
