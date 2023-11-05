package com.github.dhslrl321.mybatch.job.todos.step

import com.github.dhslrl321.mybatch.job.todos.entity.Todo
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TodoItemWriter(
    private val jdbcTemplate: JdbcTemplate,
) : ItemWriter<Todo> {

    @Transactional
    override fun write(chunk: Chunk<out Todo>) {
        println("[writer] chunk size: ${chunk.size()}")
        val sql = "UPDATE todos SET name = ?, status = ?, updated_at = ? WHERE id = ?"

        chunk.items.forEach { todo ->
            jdbcTemplate.update(
                sql,
                todo.name,
                todo.status,
                todo.updatedAt,
                todo.id
            )
        }
    }
}
