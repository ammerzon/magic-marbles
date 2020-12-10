package com.ammerzon.dto.game

import com.ammerzon.entity.game.GameState
import io.micronaut.core.annotation.Introspected

/**
 * A game session hold the current game state, the board and the score.
 */
@Introspected
data class GameSessionDto(val gameBoard: GameBoardDto, val score: ScoreDto, val state: GameState) {
}
