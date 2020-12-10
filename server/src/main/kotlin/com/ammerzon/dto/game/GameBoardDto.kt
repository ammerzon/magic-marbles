package com.ammerzon.dto.game

import com.ammerzon.entity.MoveNotPossible
import com.ammerzon.entity.Size
import com.ammerzon.entity.game.Marble
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.micronaut.core.annotation.Introspected
import kotlin.random.Random

/**
 * A game board which contains a two dimensional array.
 */
@Introspected
data class GameBoardDto(val size: Size, val board: Array<Array<Marble>>) {
}