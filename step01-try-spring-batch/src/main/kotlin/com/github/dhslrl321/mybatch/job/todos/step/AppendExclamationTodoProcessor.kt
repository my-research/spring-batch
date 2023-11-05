package com.github.dhslrl321.mybatch.job.todos.step

import com.github.dhslrl321.mybatch.job.todos.entity.Todo
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class AppendExclamationTodoProcessor: ItemProcessor<Todo, Todo> {
    override fun process(item: Todo): Todo {
        item.appendExclamationToName()
        println("[processor(!)]: $item")
        return item
    }
}
