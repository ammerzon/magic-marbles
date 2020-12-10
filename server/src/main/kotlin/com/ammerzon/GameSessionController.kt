package com.ammerzon

import com.ammerzon.dto.PositionDto
import com.ammerzon.dto.SizeDto
import com.ammerzon.dto.game.GameSessionDto
import com.ammerzon.entity.Position
import com.ammerzon.entity.Size
import com.ammerzon.entity.game.GameSession
import com.ammerzon.service.GameService
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

@Controller("/api/game")
@Validated
class GameSessionController @Inject constructor(private val gameService: GameService) {
    private val logger = LoggerFactory.getLogger(GameSessionController::class.java)

    @Post
    fun startGame(@Body @Valid @NotNull size: SizeDto): Single<GameSessionDto> {
        return gameService.startGame(size)
    }

    @Post("/move")
    fun makeMove(@Body @Valid @NotNull position: PositionDto): Maybe<GameSessionDto> {
        return gameService.makeMove(position)
    }
}