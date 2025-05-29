package org.turkuvaz.turkuvapp.rate.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.turkuvaz.turkuvapp.R

@Composable
fun TMDRatingLayout(
    starCount: Int = 10,
    starSize: Dp = 30.dp,
    spacerSize: Dp = 5.dp,
    bgColor: Color = Color.White,
    isVertical: Boolean = false,
    onRated: (value: Float) -> Unit = {}
) {
    val progress = remember { mutableFloatStateOf(0f) }
    var fullAreaLength = 0
    val enabledAreaLength = (starSize * progress.floatValue * starCount) + (spacerSize * progress.floatValue * starCount)

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .onPlaced {
                    fullAreaLength = if (isVertical) it.size.height else it.size.width
                }
                .pointerInput(Unit) {
                    detectTapGestures { touchPoint ->
                        progress.floatValue = ((if (isVertical) touchPoint.y else touchPoint.x) / fullAreaLength).coerceIn(0f, 1f)
                        onRated(starCount * progress.floatValue)
                    }
                }
                .pointerInput(Unit) {
                    detectTransformGestures { touchPoint, _, _, _ ->
                        progress.floatValue = ((if (isVertical) touchPoint.y else touchPoint.x) / fullAreaLength).coerceIn(0f, 1f)
                        onRated(starCount * progress.floatValue)
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .width(if (isVertical) starSize else enabledAreaLength)
                    .height(if (isVertical) enabledAreaLength else starSize)
                    .background(Color.Yellow)
            )
            StarsLayout(isVertical = isVertical) {
                repeat(starCount) {
                    Image(
                        painter = painterResource(R.drawable.star_outline),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(bgColor),
                        modifier = Modifier.size(starSize)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(if (isVertical) starSize else spacerSize)
                            .height(if (isVertical) spacerSize else starSize)
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
private fun StarsLayout(isVertical: Boolean = false, Content: @Composable () -> Unit) {
    if (isVertical) {
        Column {
            Content()
        }
    } else {
        Row {
            Content()
        }
    }
}