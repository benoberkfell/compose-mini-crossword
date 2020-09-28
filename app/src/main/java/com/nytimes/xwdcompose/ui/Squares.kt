package com.nytimes.xwdcompose.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nytimes.xwdcompose.data.BoardData.board
import com.nytimes.xwdcompose.data.SelectionMode
import com.nytimes.xwdcompose.data.Square
import com.nytimes.xwdcompose.data.SquareType

@Composable
fun StrikeOut() {
    val strokeWidth = with(DensityAmbient.current) {
        5.dp.toPx()
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(Color.Red, Offset(this.size.width, 0f),
                Offset(0f, this.size.height),
                strokeWidth = strokeWidth)
    }
}



@Composable
fun LetterSquare(modifier: Modifier = Modifier.fillMaxSize(),
                 square: Square,
                 onClick: (Square) -> Unit) {

    Box(modifier = modifier.clickable(onClick = {onClick(square)})) {
        WithConstraints {
            val textSize = with(DensityAmbient.current) {
                (maxWidth.toPx() * .6f).toSp()
            }

            val numberSize = with(DensityAmbient.current) {
                (maxWidth.toPx() * .15f).toSp()
            }

            Stack {
                ColoredSquareBackground(
                        color = CellColorHelper.colorForSelectionMode(square.selectionMode),
                        modifier = Modifier
                                .fillMaxSize()
                                .gravity(Alignment.Center))

                square.userAnswer?.let { userAnswer ->
                    Text(text = userAnswer,
                            style = TextStyle(fontSize = textSize),
                            modifier = Modifier.gravity(Alignment.Center))
                }

                square.cellNumber?.let {
                    Text(text = it,
                            style = TextStyle(fontSize = numberSize),
                            modifier = Modifier.gravity(Alignment.TopStart)
                                    .padding(3.dp))
                }

                square.userAnswer?.let { userAnswer ->
                    if (square.checked && userAnswer != square.answer) {
                        StrikeOut()
                    }
                }
            }
        }
    }
}


@Composable
fun ColoredSquareBackground(
        color: Color,
        modifier: Modifier) {
    Box(
            modifier = modifier,
            backgroundColor = color,
            shape = RectangleShape
    )
}

@Composable
fun WhiteSquareBackground(modifier: Modifier = Modifier.fillMaxSize()) {
    ColoredSquareBackground(
            color = CellBackground.NONE.color,
            modifier = modifier)
}

@Composable
fun BlackSquareBackground(modifier: Modifier = Modifier.fillMaxSize()) {
    ColoredSquareBackground(
            color = CellBackground.BLACK_SQUARE.color,
            modifier = modifier)
}



enum class CellBackground constructor(val color: Color) {
    NONE(Color.White),
    CURSOR(Color(red = 0xFF, green = 0xDA, blue = 0x00)),
    ACTIVE_CLUE(Color(red = 0xA7, green = 0xD8, blue = 0xFF)),
    BLACK_SQUARE(Color.Black)
}

object CellColorHelper {
    fun colorForSelectionMode(mode: SelectionMode) = when(mode) {
        SelectionMode.NONE -> CellBackground.NONE.color
        SelectionMode.CURSOR -> CellBackground.CURSOR.color
        SelectionMode.ACTIVE_CLUE -> CellBackground.ACTIVE_CLUE.color
    }
}