package com.nytimes.xwdcompose.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nytimes.xwdcompose.data.BoardData
import com.nytimes.xwdcompose.data.Clue
import com.nytimes.xwdcompose.data.Square
import com.nytimes.xwdcompose.data.SquareType
import kotlin.math.sqrt

val CELL_BORDER_WIDTH = 1.dp

@Composable
fun GameBoard(squares: List<Square>,
              squareOnClick: (Square) -> Unit) {

    val edgeCount = sqrt(squares.size.toDouble()).toInt()

    WithConstraints {
        val squareWidth = maxWidth / BoardData.board.edgeCount

        Box {
            OuterHorizontalBorder()

            squares.chunked(edgeCount).withIndex().forEach { (rowIndex, row) ->
                Row {
                    row.withIndex().forEach { (squareIndex, square) ->
                        when (square.squareType) {
                            SquareType.LETTER ->
                                LetterSquare(
                                    modifier = Modifier.preferredSize(squareWidth),
                                    square = square,
                                    onClick = {
                                        squareOnClick(it)
                                    }
                                )
                            SquareType.BLACK -> BlackSquareBackground(
                                modifier = Modifier.preferredSize(squareWidth),
                            )
                        }
                        when (squareIndex + 1) {
                            edgeCount -> OuterVerticalBorder(squareWidth)
                            else -> InnerVerticalBorder(squareWidth)
                        }
                    }
                }
                when (rowIndex + 1) {
                    edgeCount -> OuterHorizontalBorder()
                    else -> InnerHorizontalBorder()
                }
            }
        }
    }
}

@Composable
fun InnerVerticalBorder(borderHeight: Dp) {
    Box(modifier = Modifier.preferredWidth(CELL_BORDER_WIDTH)
            .preferredHeight(borderHeight),
            backgroundColor = Color.LightGray)
}

@Composable
fun OuterVerticalBorder(squareWidth: Dp) {
    Column {
        Box(modifier = Modifier.preferredWidth(CELL_BORDER_WIDTH)
                .preferredHeight(squareWidth),
                backgroundColor = Color.Black)
    }
}

@Composable
fun OuterHorizontalBorder() {
    Row {
        Box(modifier = Modifier.fillMaxWidth()
                .preferredHeight(CELL_BORDER_WIDTH),
                backgroundColor = Color.Black)
    }
}

@Composable
fun InnerHorizontalBorder() {
    Box(modifier = Modifier.fillMaxWidth()
            .preferredHeight(CELL_BORDER_WIDTH),
            backgroundColor = Color.LightGray)
}

@Composable
fun ClueBar(clue: Clue) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(red = 0xA7, green = 0xD8, blue = 0xFF)) {
        Text(clue.clueText, modifier = Modifier.padding(10.dp))
    }
}


