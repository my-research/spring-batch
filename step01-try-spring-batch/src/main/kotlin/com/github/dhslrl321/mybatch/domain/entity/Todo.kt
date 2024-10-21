package com.github.dhslrl321.mybatch.domain.entity

data class Todo(
  val id: Long,
  var name: String,
  var status: String,
  val createdAt: String,
  val updatedAt: String,
) {
  fun appendExclamationToName() {
    this.name = this.name.plus("!")
  }

  fun appendQuestionMarkToName() {
    this.name = this.name.plus("?")
  }

}
