package net.rafgpereira.coinwatcher.ui.common

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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

// Based on https://raw.githubusercontent.com/metehanbolatt/LineChartCompose/refs/heads/master/app/src/main/java/com/metehanbolat/linechartcompose/LineChart.kt
@Composable
fun LineChart(
    data: List<Pair<Int, Double>> = emptyList(),
    colors: LineChartDefaults.LineChartColors = LineChartDefaults.colors(),
    style: LineChartDefaults.LineChartStyle = LineChartDefaults.style(),
    shouldDrawLine: Boolean = true,
    shouldDrawFill: Boolean = true,
    shouldDrawAxis: Boolean = true,
    modifier: Modifier = Modifier
) {
    val upperValue = remember { (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0 }
    val lowerValue = remember { (data.minOfOrNull { it.second }?.toInt() ?: 0) }
    val axisTextPaint = remember(LocalDensity.current) { colors.axisTextPaint }

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
                drawContext, size.height, data, spacePerTime, axisTextPaint, upperValue, lowerValue)
        if (shouldDrawLine) drawLine(path, colors.lineColor, style.lineStroke)
        if (shouldDrawFill) drawFill(path, style.fillBrush)
    }
}

object LineChartDefaults {
    private val DEFAULT_LINE_STROKE_WIDTH = 2.dp
    private const val DEFAULT_FILL_GRADIENT_ALPHA = 0.1f
    private val DEFAULT_AXIS_TEXT_SIZE = 12.sp

    @Composable
    fun colors(
        axisTextPaint: Paint = defaultAxisPaint(),
        lineColor: Color = MaterialTheme.colorScheme.primary,
        fillColors: List<Color> =
            listOf(lineColor, remember { lineColor.copy(DEFAULT_FILL_GRADIENT_ALPHA) })
    ) = LineChartColors(axisTextPaint, lineColor, fillColors)

    @Composable
    fun style(
        lineStroke: Stroke = defaultLineStroke(),
        fillBrush: Brush = Brush.verticalGradient(colors().fillColors)
    ) = LineChartStyle(lineStroke, fillBrush)

    @Immutable
    class LineChartColors(
        val axisTextPaint: Paint,
        val lineColor: Color,
        val fillColors: List<Color>
    )

    @Immutable
    class LineChartStyle(
        val lineStroke: Stroke,
        val fillBrush: Brush
    )

    @Composable
    private fun defaultAxisPaint() = Paint().apply {
        color = MaterialTheme.colorScheme.primary.toArgb()
        textAlign = Paint.Align.CENTER
        textSize = LocalDensity.current.run { DEFAULT_AXIS_TEXT_SIZE.toPx() }
    }

    @Composable
    private fun defaultLineStroke() =
        Stroke(
            width = LocalDensity.current.run { DEFAULT_LINE_STROKE_WIDTH.toPx() },
            cap = StrokeCap.Round
        )
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

private fun DrawScope.drawLine(path: Path, color: Color, stroke: Stroke) =
    drawPath(
        path = path,
        color = color,
        style = stroke
    )

private fun DrawScope.drawFill(path: Path, brush: Brush) {
    val fillPath = android.graphics.Path(path.asAndroidPath()).asComposePath().apply {
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

    drawPath(path = fillPath, brush = brush)
}