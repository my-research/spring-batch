package com.github.dhslrl321.mybatch.batch.job

import com.github.dhslrl321.mybatch.batch.steps.commons.StepResultListener
import com.github.dhslrl321.mybatch.batch.steps.commons.TodoItemReaders
import com.github.dhslrl321.mybatch.batch.steps.commons.TodoItemWriter
import com.github.dhslrl321.mybatch.batch.steps.step1.AppendExclamationTodoProcessor
import com.github.dhslrl321.mybatch.batch.steps.step2.AppendQuestionMarkTodoProcessor
import com.github.dhslrl321.mybatch.domain.entity.Todo
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.skip.SkipPolicy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class TodoBatchJobConfig(
  private val reader: TodoItemReaders,
  private val appendExclamationProcessor: AppendExclamationTodoProcessor,
  private val appendQuestionMarkProcessor: AppendQuestionMarkTodoProcessor,
  private val writer: TodoItemWriter,
  private val resultListener: StepResultListener,
  private val skipPolicy: SkipPolicy,
) {

  companion object {
    const val JOB_NAME = "todo"
    private const val FIRST_STEP = "highlight"
    private const val SECOND_STEP = "qMark"
  }

  @Bean(JOB_NAME)
  fun doneTodos(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
    return JobBuilder(JOB_NAME, jobRepository)
      .start(first(jobRepository, transactionManager)) // first job
//      .next(second(jobRepository, transactionManager))
      .listener(resultListener)
      .build()
  }

  @Bean(JOB_NAME + FIRST_STEP)
  fun first(
    jobRepository: JobRepository,
    transactionManager: PlatformTransactionManager,
  ): Step {
    return StepBuilder(FIRST_STEP, jobRepository)
      .chunk<Todo, Todo>(2, transactionManager)
      .reader(reader.jdbcCursorItemReader())
      .processor(appendExclamationProcessor) // only one processor
      .writer(writer)
      .faultTolerant()
      .skipPolicy(skipPolicy)
      .build()
  }

  @Bean(JOB_NAME + SECOND_STEP)
  fun second(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
    return StepBuilder(SECOND_STEP, jobRepository)
      .chunk<Todo, Todo>(2, transactionManager)
      .reader(reader.jdbcCursorItemReader())
      .processor(appendQuestionMarkProcessor) // only one processor
      .writer(writer)
      .build()
  }
}
