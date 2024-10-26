package com.github.dhslrl321.mybatch.batch.steps.commons

import org.springframework.batch.core.step.skip.SkipPolicy
import org.springframework.stereotype.Component

@Component
class DefaultSkipPolicy: SkipPolicy {
  override fun shouldSkip(t: Throwable, skipCount: Long): Boolean {
    return when (t) {
      is IllegalStateException -> true
      else -> false
    }
  }
}
