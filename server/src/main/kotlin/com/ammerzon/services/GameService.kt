package com.ammerzon.services

import com.ammerzon.model.Position
import com.ammerzon.model.Size
import com.ammerzon.model.game.GameSession
import io.reactivex.Maybe
import io.reactivex.Single

interface GameService {
    fun startGame(size: Single<Size>): Single<GameSession>
    fun makeMove(pos: Single<Position>): Maybe<GameSession>
}