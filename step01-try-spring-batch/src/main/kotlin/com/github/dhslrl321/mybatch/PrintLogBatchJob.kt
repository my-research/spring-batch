package com.github.dhslrl321.mybatch

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
class PrintLogBatchJob {

    companion object {
        private const val JOB_NAME = "PRINT_LOG_JOB"
        private const val STEP_NAME = "PRINT_LOG_STEP"
    }

    @Bean
    fun printLogJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(printLogStep(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun printLogStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder(STEP_NAME, jobRepository)
            .tasklet(printLogTasklet(), transactionManager)
            .build()
    }

    @Bean
    fun printLogTasklet(): Tasklet {
        return Tasklet { _, _ ->
            logger.info {"Hello Spring Batch!"}
            null
        }
    }
}
