package com.ammerzon.model.game

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

/**
 * Immutable class for describing a score entry.
 */
@Introspected
data class Score(val time: LocalDateTime, val points: Int)