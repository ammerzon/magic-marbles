package com.ammerzon.logic.checker

import com.ammerzon.entity.Position
import com.ammerzon.entity.game.GameBoard
import com.ammerzon.entity.game.Marble
import javax.inject.Singleton

@Singleton
class ClassicGameBoardChecker : GameBoardChecker {

    override fun hasGameEnded(gameBoard: GameBoard): Boolean {
        val flattenedBoard = gameBoard.board.flatten()

        // The whole game board is empty
        if (flattenedBoard.all { it == Marble.EMPTY })
            return true

        // Search for a possible move
        return !gameBoard.board.withIndex().any { (i, col) -> col.withIndex().any { (j, _) -> movePossible(gameBoard, Position(i, j)) } }
    }

    override fun movePossible(gameBoard: GameBoard, position: Position): Boolean {
        return getConnectedMarbles(gameBoard, position).size >= 2
    }

    override fun getConnectedMarbles(gameBoard: GameBoard, position: Position): List<Position> {
        val columns = gameBoard.size.width
        val rows = gameBoard.size.height

        fun connectedMarbles(position: Position, marbles: MutableList<Position>): List<Position> {
            for (y in position.y - 1..position.y + 1) {
                for (x in position.x - 1..position.x + 1) {
                    if (x >= 0 && y >= 0 && x < columns && y < rows && !(y == position.y && x == position.x)) {
                        val newPosition = Position(x, y)
                        if (!marbles.contains(newPosition) && gameBoard.board[x][y] == gameBoard.board[position.x][position.y]) {
                            marbles.add(newPosition)
                            connectedMarbles(newPosition, marbles)
                        }
                    }
                }
            }
            return marbles
        }

        return connectedMarbles(position, mutableListOf())
    }
}