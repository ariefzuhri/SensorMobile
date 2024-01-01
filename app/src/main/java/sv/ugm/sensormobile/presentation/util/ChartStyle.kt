package sv.ugm.sensormobile.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.DefaultColors
import com.patrykandpatrick.vico.core.DefaultDimens
import com.patrykandpatrick.vico.core.chart.DefaultPointConnector
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders

val defaultChartColors =
    @Composable {
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary,
        )
    }

@Composable
fun rememberChartStyle(
    columnChartColors: List<Color>,
    lineChartColors: List<Color>,
    showDataPoint: Boolean = true,
): ChartStyle {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colorScheme = MaterialTheme.colorScheme
    
    return remember(
        columnChartColors,
        lineChartColors,
        isSystemInDarkTheme,
    ) {
        val defaultColors =
            if (isSystemInDarkTheme) DefaultColors.Dark else DefaultColors.Light
        
        ChartStyle(
            ChartStyle.Axis(
                axisLabelColor = Color(defaultColors.axisLabelColor),
                axisGuidelineColor = Color(defaultColors.axisGuidelineColor),
                axisLineColor = Color(defaultColors.axisLineColor),
            ),
            ChartStyle.ColumnChart(
                columnChartColors.map { columnChartColor ->
                    LineComponent(
                        columnChartColor.toArgb(),
                        DefaultDimens.COLUMN_WIDTH,
                        Shapes.roundedCornerShape(DefaultDimens.COLUMN_ROUNDNESS_PERCENT),
                    )
                },
            ),
            ChartStyle.LineChart(
                lineChartColors.map { lineChartColor ->
                    LineChart.LineSpec(
                        lineColor = lineChartColor.toArgb(),
                        lineBackgroundShader = DynamicShaders.fromBrush(
                            Brush.verticalGradient(
                                listOf(
                                    lineChartColor.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                                    lineChartColor.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END),
                                ),
                            ),
                        ),
                        pointConnector = DefaultPointConnector(),
                        pointSizeDp = 10f,
                        point = ShapeComponent(
                            shape = Shapes.pillShape,
                            color = lineChartColor.toArgb(),
                            strokeWidthDp = 1f,
                            strokeColor = colorScheme.surface.toArgb(),
                        ).takeIf { showDataPoint },
                    )
                },
            ),
            ChartStyle.Marker(),
            Color(defaultColors.elevationOverlayColor),
        )
    }
}

@Composable
fun rememberChartStyle(
    chartColors: List<Color> = defaultChartColors(),
    showDataPoint: Boolean = true,
): ChartStyle {
    return rememberChartStyle(
        columnChartColors = chartColors,
        lineChartColors = chartColors,
        showDataPoint = showDataPoint,
    )
}