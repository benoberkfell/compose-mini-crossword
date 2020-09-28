package com.nytimes.xwdcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.xwdcompose.data.*
import java.util.*

class BoardViewModel : ViewModel() {


    private val board = BoardData.board

    private var selectedDirection = Direction.ACROSS
    private var selectedCellIndex: Int
    private var selectedClue: Clue
    private var squares: List<Square> = board.squares

    var boardState by mutableStateOf<BoardState?>(null, referentialEqualityPolicy())

    init {
        selectedDirection = Direction.ACROSS
        selectedClue = board.clues.first { it.direction == selectedDirection }
        selectedCellIndex = selectedClue.cells.first()
        emitBoardState()
        selectSquare(squares[selectedCellIndex])
    }

    private fun emitBoardState() {
        boardState = BoardState(
            squares, selectedCellIndex, selectedClue
        )
    }

    fun enterLetter(letter: String) {
        squares = squares.toMutableList().also {
            it[selectedCellIndex].userAnswer = letter.toUpperCase(Locale.US)
            it[selectedCellIndex].checked = false
        }

        emitBoardState()
        advanceCursorToNextSquare()
    }

    fun checkBoard() {
        squares = squares.map {
                it.checked = true
                it
            }
        emitBoardState()
    }

    private fun advanceCursorToNextSquare() {
        val cursorIndexForCurrentClue = selectedClue.cells.indexOf(selectedCellIndex)
        if (cursorIndexForCurrentClue != selectedClue.cells.lastIndex) {
            selectIndex(selectedClue.cells[cursorIndexForCurrentClue + 1])
        } else {
            val clueIndex = board.clues.indexOf(selectedClue)
            val nextClue = if (clueIndex == board.clues.lastIndex) {
                board.clues[0]
            } else {
                board.clues[clueIndex + 1]
            }
            selectIndex(nextClue.cells[0])
        }
    }

    private fun selectIndex(index: Int) {
        selectSquare(squares[index])
    }

    fun selectSquare(square: Square) {
        val newIndex = squares.indexOf(square)

        if (selectedCellIndex == newIndex) {
            selectedDirection = when (selectedDirection) {
                Direction.ACROSS -> Direction.DOWN
                Direction.DOWN -> Direction.ACROSS
            }
        }

        selectedCellIndex = newIndex

        //deselect old
        selectedClue.cells.forEach { index ->
            squares[index].selectionMode = SelectionMode.NONE
        }

        selectedClue = board.clues.first {
            it.direction == selectedDirection && it.cells.contains(newIndex)
        }

        selectedClue.cells.forEach { index ->
            squares[index].selectionMode = SelectionMode.ACTIVE_CLUE
        }

        squares[newIndex].selectionMode = SelectionMode.CURSOR
        emitBoardState()
    }
}

data class BoardState(val squares: List<Square>,
                      val selectedCellIndex: Int,
                      val selectedClue: Clue)