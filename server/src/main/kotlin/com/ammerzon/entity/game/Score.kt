package com.ammerzon.entity.game

import com.ammerzon.dto.game.ScoreDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




/**
 * Immutable class for describing a score entry.
 */
data class Score(var time: LocalDateTime, var points: Int) {
    var formatter = DateTimeFormatter.ISO_DATE_TIME

    fun scoreDto(): ScoreDto {
        return ScoreDto(
                time = time.format(formatter),
                points = points,
        )
    }
}