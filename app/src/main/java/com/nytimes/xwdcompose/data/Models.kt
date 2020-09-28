package com.nytimes.xwdcompose.data

import java.util.*

enum class SquareType {
    BLACK,
    LETTER
}

enum class SelectionMode {
    NONE, // plain old white square
    CURSOR, // the cursor is on this square
    ACTIVE_CLUE // this is the clue we're solving, but cursor's not here
}

enum class Direction {
    ACROSS,
    DOWN
}

data class Clue(val direction: Direction, val cells: List<Int>, val clueText: String)

data class Square constructor(
        val squareType: SquareType,
        val answer: String? = null,
        val cellNumber: String? = null,
        var userAnswer: String? = null,
        var checked: Boolean = false,
        var selectionMode: SelectionMode = SelectionMode.NONE) {

    companion object {
        fun forLetter(answer: String, cellNumber: String? = null): Square {
            return Square(
                    squareType = SquareType.LETTER,
                    answer = answer,
                    cellNumber = cellNumber,
                    selectionMode = SelectionMode.NONE
            )
        }

        fun forBlack() : Square {
            return Square(squareType = SquareType.BLACK)
        }
    }
}

data class Board(val edgeCount: Int,
            val squares: List<Square>,
            val clues: List<Clue>)