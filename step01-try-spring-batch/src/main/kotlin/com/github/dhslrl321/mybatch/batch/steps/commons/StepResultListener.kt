package com.github.dhslrl321.mybatch.batch.steps.commons

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class StepResultListener: JobExecutionListener {
  override fun beforeJob(jobExecution: JobExecution) {
    println("hello $jobExecution")
  }

  override fun afterJob(jobExecution: JobExecution) {

    val failedCount = jobExecution.stepExecutions.stream().map { it.filterCount }.reduce { t, u -> t + u }
    val skipCount = jobExecution.stepExecutions.stream().map { it.skipCount }.reduce { t, u -> t + u }

    println("$failedCount $skipCount")
  }
}
