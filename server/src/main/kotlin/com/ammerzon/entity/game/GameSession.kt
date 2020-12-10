package com.ammerzon.entity.game

import com.ammerzon.dto.game.GameSessionDto
import com.ammerzon.entity.GameAlreadyFinished
import com.ammerzon.entity.GameError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.time.LocalDateTime

/**
 * A game session hold the current game state, the board and the score.
 */
data class GameSession(val gameBoard: GameBoard, var score: Score, var state: GameState = GameState.RUNNING) {

    fun hasValidState(): Result<Boolean, GameError> = when (state) {
        GameState.FINISHED -> Err(GameAlreadyFinished)
        else -> Ok(true)
    }

    fun addPoints(points: Int) {
        score.time = LocalDateTime.now()
        score.points += points
    }

    fun gameSessionDto(): GameSessionDto {
        return GameSessionDto(
                gameBoard = gameBoard.gameBoardDto(),
                score = score.scoreDto(),
                state = state
        )
    }
}
