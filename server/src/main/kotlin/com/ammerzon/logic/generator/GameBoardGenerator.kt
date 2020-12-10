package com.ammerzon.logic.generator

import com.ammerzon.entity.Size
import com.ammerzon.entity.game.Marble

interface GameBoardGenerator {
    fun generate(size: Size): Array<Array<Marble>>
}