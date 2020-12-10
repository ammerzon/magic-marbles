package com.ammerzon.service

import com.ammerzon.dto.PositionDto
import com.ammerzon.dto.SizeDto
import com.ammerzon.dto.game.GameSessionDto
import com.ammerzon.entity.GameError
import com.ammerzon.entity.GameException
import com.ammerzon.entity.GameNotStarted
import com.ammerzon.entity.Size
import com.ammerzon.entity.game.GameBoard
import com.ammerzon.entity.game.GameSession
import com.ammerzon.entity.game.GameState
import com.github.michaelbull.result.Result
import com.ammerzon.entity.game.Score
import com.ammerzon.logic.checker.GameBoardChecker
import com.ammerzon.logic.generator.GameBoardGenerator
import com.github.michaelbull.result.*
import io.reactivex.Maybe
import io.reactivex.Single
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class GameServiceImpl(var checker: GameBoardChecker, var generator: GameBoardGenerator): GameService {
    lateinit var session: GameSession

    override fun startGame(size: SizeDto): Single<GameSessionDto> {
        return Single.create{emitter ->
            session = GameSession(GameBoard(Size(size.width, size.height), generator, checker), Score(LocalDateTime.now(), 0))
            emitter.onSuccess(session.gameSessionDto())
        }
    }

    override fun makeMove(pos: PositionDto): Maybe<GameSessionDto> {
        return Maybe.create{emitter ->
            canMakeMove()
                .and(session.hasValidState())
                .flatMap{ session.gameBoard.move(pos.x, pos.y)}
                .onSuccess{
                    session.addPoints(it)
                    if (checker.hasGameEnded(session.gameBoard)) {
                        session.state = GameState.FINISHED
                    }
                }
                .map { emitter.onSuccess(session.gameSessionDto()) }
                .mapError { emitter.onError(GameException(it.toString())) }
        }
    }

    private fun canMakeMove(): Result<Boolean, GameError> {
        if (this::session.isInitialized)
            return Err(GameNotStarted)

        return Ok(true)
    }
}