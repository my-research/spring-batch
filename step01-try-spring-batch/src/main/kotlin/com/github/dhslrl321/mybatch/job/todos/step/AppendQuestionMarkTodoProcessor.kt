package com.github.dhslrl321.mybatch.job.todos.step

import com.github.dhslrl321.mybatch.job.todos.entity.Todo
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class AppendQuestionMarkTodoProcessor: ItemProcessor<Todo, Todo> {
    override fun process(item: Todo): Todo {
        item.appendQuestionMarkToName()
        println("[processor(?)]: $item")
        return item
    }
}
