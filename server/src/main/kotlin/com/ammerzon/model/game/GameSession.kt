package com.ammerzon.model.game

import io.micronaut.core.annotation.Introspected

/**
 * A game session hold the current game state, the board and the score.
 */
@Introspected
data class GameSession(val state: GameState, val gameBoard: GameBoard, val score: Score)
