package com.ammerzon.entity

/**
 * All possible error that can happen.
 */
sealed class GameError

object GameAlreadyFinished : GameError()
object GameNotStarted : GameError()
object MoveNotPossible : GameError()

class GameException(message: String) : Exception(message)