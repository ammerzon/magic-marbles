package com.ammerzon.dto.game

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

/**
 * Immutable class for describing a score entry.
 */
@Introspected
data class ScoreDto(val time: String, val points: Int)