package com.ammerzon.logic.checker

import com.ammerzon.entity.Position
import com.ammerzon.entity.game.GameBoard
import com.ammerzon.entity.game.Marble

interface GameBoardChecker {
    fun hasGameEnded(gameBoard: GameBoard): Boolean
    fun getConnectedMarbles(gameBoard: GameBoard, position: Position): List<Position>
    fun movePossible(gameBoard: GameBoard, position: Position): Boolean
}