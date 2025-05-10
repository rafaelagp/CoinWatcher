package net.rafgpereira.coinwatcher.ui.common

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.math.roundToInt

// Derived from https://raw.githubusercontent.com/metehanbolatt/LineChartCompose/refs/heads/master/app/src/main/java/com/metehanbolat/linechartcompose/LineChart.kt
@Composable
fun LineChart(
    data: List<Pair<Int, Double>> = emptyList(),
    shouldDrawLine: Boolean = true,
    shouldDrawFill: Boolean = true,
    shouldDrawAxis: Boolean = true,
    modifier: Modifier = Modifier
) {
    val graphColor = Color.Cyan
    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }
    val upperValue = remember { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0 }
    val lowerValue = remember { (data.minOfOrNull { it.second }?.toInt() ?: 0) }
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerHour = size.width / data.size

        if (shouldDrawAxis) {
            (data.indices step 2).forEach { i ->
                val hour = data[i].first
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        hour.toString(),
                        i * spacePerHour,
                        size.height,
                        textPaint
                    )
                }
            }

            val priceStep = (upperValue - lowerValue) / 5f
            (0..4).forEach { i ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        round(lowerValue + priceStep * i).toString(),
                        30f,
                        size.height - i * size.height / 5f,
                        textPaint
                    )
                }
            }
        }

        val strokePath = Path().apply {
            val height = size.height
            data.indices.forEach { i ->
                val info = data[i]
                val ratio = (info.second - lowerValue) / (upperValue - lowerValue)

                val x1 = i * spacePerHour
                val y1 = height - (ratio * height).toFloat()

                if (i == 0) { moveTo(x1, y1) }
                if (i == data.indices.count() - 1) {
                    lineTo(x1, y1)
                    lineTo(size.width, size.height)
                }
                else lineTo(x1, y1)
            }
        }

        if (shouldDrawLine) {
            drawPath(
                path = strokePath,
                color = graphColor,
                style = Stroke(
                    width = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        if (shouldDrawFill) {
            val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        transparentGraphColor,
                        Color.Transparent
                    ),
                    endY = size.height
                )
            )
        }
    }
}