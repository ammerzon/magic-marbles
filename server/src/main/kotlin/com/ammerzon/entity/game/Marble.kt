package com.ammerzon.entity.game

/**
 * Enum indicating the marble type.
 */
enum class Marble(val v: Int) {
    RED(0), BLUE(1), GREEN(2), EMPTY(3);

    companion object {
        fun fromInt(value: Int) = values().first { it.v == value }
        fun amountOfColors() = Marble.values().size - 1
    }
}