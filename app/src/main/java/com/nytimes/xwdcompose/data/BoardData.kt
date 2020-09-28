package com.nytimes.xwdcompose.data

object BoardData {

        private val squares = listOf(

                Square.forBlack(),
                Square.forLetter(answer = "S", cellNumber = "1"),
                Square.forLetter(answer = "P", cellNumber = "2"),
                Square.forLetter(answer = "I", cellNumber = "3"),
                Square.forLetter(answer = "T", cellNumber = "4"),

                Square.forBlack(),
                Square.forLetter(answer = "K", cellNumber = "5"),
                Square.forLetter("E"),
                Square.forLetter("T"),
                Square.forLetter("O"),

                Square.forLetter(answer = "F", cellNumber = "6"),
                Square.forLetter("A"),
                Square.forLetter("N"),
                Square.forLetter("C"),
                Square.forLetter("Y"),

                Square.forLetter(answer = "U", cellNumber = "7"),
                Square.forLetter("T"),
                Square.forLetter("A"),
                Square.forLetter("H"),
                Square.forBlack(),

                Square.forLetter(answer = "R", cellNumber = "8"),
                Square.forLetter("E"),
                Square.forLetter("L"),
                Square.forLetter("Y"),
                Square.forBlack()
        )

        private val clues = listOf(
                Clue(direction = Direction.ACROSS,
                        cells = listOf(1, 2, 3, 4),
                        clueText = "Card Game That Rewards Speed"),

                Clue(direction = Direction.ACROSS,
                        cells = listOf(6, 7, 8, 9),
                        clueText = "Low-carb, high-fat diet, familiarly"),

                Clue(direction = Direction.ACROSS,
                        cells = listOf(10, 11, 12, 13, 14),
                        clueText = "Hi-Falutin'"),

                Clue(direction = Direction.ACROSS,
                        cells = listOf(15, 16, 17, 18),
                        clueText = "Nevada Neighbor"),

                Clue(direction = Direction.ACROSS,
                        cells = listOf(20, 21, 22, 23),
                        clueText = "Bank (on)"),

                Clue(direction = Direction.DOWN,
                        cells = listOf(1, 6, 11, 16, 21),
                        clueText = "It has wheels on a heel"),

                Clue(direction = Direction.DOWN,
                        cells = listOf(2, 7, 12, 17, 22),
                        clueText = "Prison-related"),

                Clue(direction = Direction.DOWN,
                        cells = listOf(3, 8, 13, 18, 23),
                        clueText = "Like skin that's touched poison ivy"),

                Clue(direction = Direction.DOWN,
                        cells = listOf(4, 9, 14),
                        clueText = "Common birthday gift for a kid"),

                Clue(direction = Direction.DOWN,
                        cells = listOf(10, 15, 20),
                        clueText = "Hair for a hare"))

        val board = Board(squares = squares,
                clues = clues)
}