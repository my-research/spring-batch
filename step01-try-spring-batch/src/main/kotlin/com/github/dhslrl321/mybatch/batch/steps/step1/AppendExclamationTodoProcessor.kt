package com.github.dhslrl321.mybatch.batch.steps.step1

import com.github.dhslrl321.mybatch.domain.entity.Todo
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class AppendExclamationTodoProcessor : ItemProcessor<Todo, Todo> {
  override fun process(item: Todo): Todo {
    item.appendExclamationToName()
    println("[processor(!)]: $item")
    throw IllegalStateException("hello")
    return item
  }
}
