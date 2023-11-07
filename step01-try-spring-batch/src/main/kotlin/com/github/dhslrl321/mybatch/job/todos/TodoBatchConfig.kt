package com.github.dhslrl321.mybatch.job.todos

import com.github.dhslrl321.mybatch.job.todos.step.AppendExclamationTodoProcessor
import com.github.dhslrl321.mybatch.job.todos.step.TodoItemReaders
import com.github.dhslrl321.mybatch.job.todos.step.TodoItemWriter
import com.github.dhslrl321.mybatch.job.todos.entity.Todo
import com.github.dhslrl321.mybatch.job.todos.step.AppendQuestionMarkTodoProcessor
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class TodoBatchConfig(
    private val reader: TodoItemReaders,
    private val appendExclamationProcessor: AppendExclamationTodoProcessor,
    private val appendQuestionMarkProcessor: AppendQuestionMarkTodoProcessor,
    private val writer: TodoItemWriter
) {

    companion object {
        const val JOB_NAME = "todo"
        private const val STEP_NAME = "highlight"
    }

    @Bean(JOB_NAME)
    fun doneTodos(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(appendTodoName(jobRepository, transactionManager))
            .build()
    }

    @Bean(JOB_NAME + STEP_NAME)
    fun appendTodoName(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder(STEP_NAME, jobRepository)
            .chunk<Todo, Todo>(2, transactionManager)
            .reader(reader.jdbcCursorItemReader())
            .processor(appendExclamationProcessor) // only one processor
            .writer(writer)
            .build()
    }
}
