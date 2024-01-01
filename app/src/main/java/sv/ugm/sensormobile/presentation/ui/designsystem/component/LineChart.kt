package sv.ugm.sensormobile.presentation.ui.designsystem.component

import android.text.TextUtils
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.legend.Legend
import sv.ugm.sensormobile.presentation.model.ChartSeries
import sv.ugm.sensormobile.presentation.util.rememberChartStyle
import sv.ugm.sensormobile.presentation.util.rememberMarker
import kotlin.math.roundToInt

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    series: ChartSeries,
    showDataPoint: Boolean = true,
    legend: Legend? = null,
) {
    val chartEntryModelProducer = remember(series) {
        ChartEntryModelProducer(
            series.datasets.map { dataset ->
                dataset.entries.map {
                    entryOf(
                        x = it.xValue,
                        y = it.yValue,
                    )
                }
            }
        )
    }
    
    val axisValuesOverrider = remember(series) {
        object : AxisValuesOverrider<ChartEntryModel> {
            override fun getMaxY(model: ChartEntryModel): Float {
                return model.maxY + 2f
            }
            
            override fun getMinY(model: ChartEntryModel): Float {
                return model.minY - 5f
            }
        }
    }
    
    val xAxisValueFormatter = remember(series) {
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            val valueIndex = value.toInt()
            series.xAxisLabels.getOrNull(valueIndex)
                ?: value.roundToInt().toString()
        }
    }
    
    val yAxisValueFormatter = remember(series) {
        AxisValueFormatter<AxisPosition.Vertical.Start> { value, _ ->
            val valueIndex = value.toInt()
            series.yAxisLabels.getOrNull(valueIndex)
                ?: value.roundToInt().toString()
        }
    }
    
    val xAxisItemPlacer = remember(series) {
        val maxEntrySize = series.datasets.maxOfOrNull { it.entries.size } ?: 0
        AxisItemPlacer.Horizontal.default(
            spacing = maxEntrySize / 10,
            offset = maxEntrySize / 20,
        )
    }
    
    val xAxis = rememberBottomAxis(
        guideline = null,
        label = axisLabelComponent(
            verticalMargin = 4.dp,
            ellipsize = TextUtils.TruncateAt.MARQUEE,
        ),
        tick = null,
        valueFormatter = xAxisValueFormatter,
        labelRotationDegrees = 90f,
        itemPlacer = xAxisItemPlacer,
    ).takeIf { series.datasets.any { it.entries.isNotEmpty() } }
    
    val yAxis = rememberStartAxis(
        valueFormatter = yAxisValueFormatter,
    ).takeIf { series.datasets.any { it.entries.isNotEmpty() } }
    
    val marker = rememberMarker(
        labelFormatter = { markedEntries, _ ->
            val (xValue, yValue) = markedEntries.first().entry.apply {
                x to y
            }
            
            // TODO: The library doesn't yet support retrieving the entry index of marked entries
            val entryIndex = 0
            val xIndex = xValue.toLong()
            val yIndex = yValue.toLong()
            
            val x: String =
                series.datasets.getOrNull(entryIndex)
                    ?.entries?.getOrNull(xIndex.toInt())?.xMarkerLabel
                    ?: xValue.toString()
            val y: String =
                series.datasets.getOrNull(entryIndex)
                    ?.entries?.getOrNull(yIndex.toInt())?.yMarkerLabel
                    ?: yValue.toString()
            
            "$x: $y"
        },
    )
    
    ProvideChartStyle(rememberChartStyle(showDataPoint = showDataPoint)) {
        Chart(
            chart = lineChart(
                axisValuesOverrider = axisValuesOverrider,
            ),
            chartModelProducer = chartEntryModelProducer,
            startAxis = yAxis,
            bottomAxis = xAxis,
            marker = marker,
            legend = legend,
            chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
            diffAnimationSpec = remember { snap() },
            modifier = modifier
                .height(260.dp),
        )
    }
}