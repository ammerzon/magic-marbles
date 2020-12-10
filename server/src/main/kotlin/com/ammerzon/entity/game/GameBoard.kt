package com.ammerzon.entity.game

import com.ammerzon.dto.game.GameBoardDto
import com.ammerzon.entity.MoveNotPossible
import com.ammerzon.entity.Position
import com.ammerzon.entity.Size
import com.ammerzon.logic.checker.GameBoardChecker
import com.ammerzon.logic.generator.GameBoardGenerator
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

/**
 * A game board which contains a two dimensional array.
 */
class GameBoard constructor(val size: Size,
                            private val generator: GameBoardGenerator,
                            private val checker: GameBoardChecker) {
    var board: Array<Array<Marble>>
        private set

    init {
        do {
            board = generator.generate(size)
        } while (checker.hasGameEnded(this))
    }

    fun move(x: Int, y: Int): Result<Int, MoveNotPossible> {
        val pos = Position(x, y)
        if (!checker.movePossible(this, pos))
            Err(MoveNotPossible)

        val positions = checker.getConnectedMarbles(this, pos)
        positions.forEach { board[it.x][it.y] = Marble.EMPTY }

        // TODO: Move marbles
        // TODO: Calculate score
        return Ok(1)
    }

    fun gameBoardDto(): GameBoardDto {
        return GameBoardDto(
                board = board,
                size = size
        )
    }
}