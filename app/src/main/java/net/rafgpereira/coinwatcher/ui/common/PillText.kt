package net.rafgpereira.coinwatcher.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PillText(
    modifier: Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    textStyle: TextStyle,
    minWidth: Dp = 60.dp,
) = Box(
    modifier = modifier
        .background(
            color = backgroundColor,
            shape = RoundedCornerShape(25.dp),
        )
        .widthIn(min = minWidth),

    ) {
    Text(
        text = text,
        modifier = Modifier
            .align(Alignment.Center)
            .padding(8.dp),
        color = textColor,
        style = textStyle,
    )
}