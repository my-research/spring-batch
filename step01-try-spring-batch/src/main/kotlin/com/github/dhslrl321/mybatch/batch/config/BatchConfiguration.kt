package com.github.dhslrl321.mybatch.batch.config

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
  @Bean
  @Throws(Exception::class)
  fun jobLauncher(): JobLauncher {
    val jobLauncher = TaskExecutorJobLauncher()
    jobLauncher.setJobRepository(jobRepository)
    jobLauncher.afterPropertiesSet()
    return jobLauncher
  }
}
