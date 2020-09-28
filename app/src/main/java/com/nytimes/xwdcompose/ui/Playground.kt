package com.nytimes.xwdcompose.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nytimes.xwdcompose.data.*
import com.nytimes.xwdcompose.data.SelectionMode.*
import com.nytimes.xwdcompose.ui.CellColorHelper.colorForSelectionMode

val sizedTo100 = Modifier.preferredSize(100.dp)

@Composable
fun ADarkLightSquarePreview() {
    Column() {
        Row {
            WhiteSquare(
                    selectionMode = ACTIVE_CLUE,
                    modifier = sizedTo100)
        }
        Row {
            WhiteSquare(
                    selectionMode = CURSOR,
                    modifier = sizedTo100)
        }
        Row {
            WhiteSquare(
                    selectionMode = NONE,
                    modifier = sizedTo100
            )
        }
    }
}

@Composable
fun ScaledTextPreview() {
    Column() {
        Row {
            WhiteSquare(
                    text = "A",
                    selectionMode = NONE,
                    cellNumber = "12",
                    strikeOut = true,
                    modifier = Modifier.preferredSize(50.dp))
        }
    }
}

@Preview
@Composable
fun PreviewGame() {
    Box(modifier = Modifier.preferredSize(100.dp)) {
        GameTable(board = BoardData.board)
    }
}

@Composable
fun GameTable(board: Board) {
    WithConstraints {
        val squareWidth = maxWidth / BoardData.board.edgeCount

        Box {
            board.squares.chunked(board.edgeCount).forEach { row ->
                Row {
                    row.forEach { square ->
                        when (square.squareType) {
                            SquareType.LETTER ->
                                LetterSquare(
                                        modifier = Modifier.preferredSize(squareWidth),
                                        square = square,
                                        onClick = {}
                                )
                            SquareType.BLACK -> BlackSquareBackground(
                                    modifier = Modifier.preferredSize(squareWidth),
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun WhiteSquare(modifier: Modifier = Modifier.fillMaxSize(),
                selectionMode: SelectionMode,
                text: String? = null,
                cellNumber: String? = null,
                strikeOut: Boolean = false) {

    Box(modifier = modifier) {
        WithConstraints {
            val textSize = with(DensityAmbient.current) {
                (maxWidth.toPx() * .6f).toSp()
            }

            val numberSize = with(DensityAmbient.current) {
                (maxWidth.toPx() * .15f).toSp()
            }

            Stack {
                ColoredSquareBackground(
                        color = colorForSelectionMode(selectionMode),
                        modifier = Modifier
                                .fillMaxSize()
                                .gravity(Alignment.Center))

                text?.let { userAnswer ->
                    Text(text = userAnswer,
                            style = TextStyle(fontSize = textSize),
                            modifier = Modifier.gravity(Alignment.Center))
                }

                cellNumber?.let {
                    Text(text = it,
                            style = TextStyle(fontSize = numberSize),
                            modifier = Modifier.gravity(Alignment.TopStart)
                                    .padding(3.dp))
                }

                if (strikeOut) {
                    StrikeOut()
                }
            }
        }
    }
}

@Composable
fun BlackSquare(modifier: Modifier = Modifier.fillMaxSize()) {
    ColoredSquareBackground(
            color = Color.Black,
            modifier = modifier)
}