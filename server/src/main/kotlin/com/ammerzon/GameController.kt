package com.ammerzon

import com.ammerzon.model.Position
import com.ammerzon.model.Size
import com.ammerzon.model.game.GameSession
import com.ammerzon.services.GameService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import io.reactivex.Maybe
import io.reactivex.Single
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Controller("api/game")
@Validated
class GameController @Inject constructor(private val gameService: GameService) {
    private val logger = LoggerFactory.getLogger(GameController::class.java)

    @Post
    fun startGame(@Body @Valid @NotNull size: Single<Size>): Single<GameSession> {
        return gameService.startGame(size)
    }

    @Put
    fun makeMove(@Body @Valid @NotNull position: Single<Position>): Maybe<GameSession> {
        return gameService.makeMove(position)
    }
}