package com.ammerzon.logic.generator

import com.ammerzon.entity.Size
import com.ammerzon.entity.game.Marble
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class RandomGameBoardGenerator: GameBoardGenerator {
    override fun generate(size: Size): Array<Array<Marble>> {
        return Array(size.width) { _ -> Array(size.height) { _ -> randomMarble() } }
    }

    private fun randomMarble(): Marble {
        return Marble.fromInt(Random.nextInt(0, Marble.amountOfColors()))
    }
}