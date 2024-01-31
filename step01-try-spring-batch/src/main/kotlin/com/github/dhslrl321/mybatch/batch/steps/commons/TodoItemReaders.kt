package com.github.dhslrl321.mybatch.batch.steps.commons

import com.github.dhslrl321.mybatch.domain.entity.Todo
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class TodoItemReaders(
    private val dataSource: DataSource,
    private val rowMapper: RowMapper<Todo>
) {
    fun jdbcCursorItemReader(): JdbcCursorItemReader<Todo> {
        return JdbcCursorItemReaderBuilder<Todo>()
            .dataSource(dataSource)
            .name("cursorTodoReader")
            .sql("SELECT id, name, status, created_at, updated_at FROM todos")
            .rowMapper(rowMapper)
            .build()
    }
}
