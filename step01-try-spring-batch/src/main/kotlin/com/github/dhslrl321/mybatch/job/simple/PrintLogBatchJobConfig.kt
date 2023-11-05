package com.github.dhslrl321.mybatch.job.simple

import com.github.dhslrl321.mybatch.logger
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
class PrintLogBatchJobConfig {

    companion object {
        const val JOB_NAME = "print log"
        const val STEP_NAME = "PRINT_LOG_STEP"
    }

    @Bean(JOB_NAME)
    fun printLogJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(printLogStep(jobRepository, transactionManager))
            .build()
    }

    @Bean(JOB_NAME + STEP_NAME)
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
