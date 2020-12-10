package com.ammerzon.service

import com.ammerzon.dto.PositionDto
import com.ammerzon.dto.SizeDto
import com.ammerzon.dto.game.GameSessionDto
import io.reactivex.Maybe
import io.reactivex.Single

interface GameService {
    fun startGame(size: SizeDto): Single<GameSessionDto>
    fun makeMove(pos: PositionDto): Maybe<GameSessionDto>
}