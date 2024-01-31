package com.github.dhslrl321.mybatch.batch.steps.step2

import com.github.dhslrl321.mybatch.domain.entity.Todo
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
