package net.rafgpereira.coinwatcher.ui.common

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawContext
import androidx.compose.ui.graphics.drawscope.DrawScope
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
        val spacePerTime = size.width / data.size
        val path = calculatePath(
            size = size,
            data = data,
            upperValue = upperValue,
            lowerValue = lowerValue,
            spacePerTime = spacePerTime
        )

        if (shouldDrawAxis)
            drawAxis(
                drawContext, size.height, data, spacePerTime, textPaint, upperValue, lowerValue)
        if (shouldDrawLine) drawLine(path, graphColor)
        if (shouldDrawFill) drawFill(path, transparentGraphColor)
    }
}

private fun calculatePath(
    size: Size,
    data: List<Pair<Int, Double>>,
    upperValue: Int,
    lowerValue: Int,
    spacePerTime: Float
) = Path().apply {
    val height = size.height
    data.indices.forEach { i ->
        val info = data[i]
        val ratio = (info.second - lowerValue) / (upperValue - lowerValue)

        val x1 = i * spacePerTime
        val y1 = height - (ratio * height).toFloat()

        if (i == 0) { moveTo(x1, y1) }
        if (i == data.indices.count() - 1) {
            lineTo(x1, y1)
            lineTo(size.width, size.height)
        }
        else lineTo(x1, y1)
    }
}

private fun drawAxis(
    drawContext: DrawContext,
    height: Float,
    data: List<Pair<Int, Double>> = emptyList(),
    spacePerTime: Float,
    textPaint: Paint,
    upperValue: Int,
    lowerValue: Int
) {
    (data.indices step 2).forEach { i ->
        val hour = data[i].first
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                hour.toString(),
                i * spacePerTime,
                height,
                textPaint
            )
        }
    }

    val spaceStep = (upperValue - lowerValue) / 5f
    (0..4).forEach { i ->
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                round(lowerValue + spaceStep * i).toString(),
                30f,
                height - i * height / 5f,
                textPaint
            )
        }
    }
}

private fun DrawScope.drawLine(strokePath: Path, graphColor: Color) {
    drawPath(
        path = strokePath,
        color = graphColor,
        style = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    )
}

private fun DrawScope.drawFill(strokePath: Path, color: Color) {
    val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

    drawPath(
        path = fillPath,
        brush = Brush.verticalGradient(
            colors = listOf(
                color,
                Color.Transparent
            ),
            endY = size.height
        )
    )
}