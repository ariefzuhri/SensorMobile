package sv.ugm.sensormobile.ui.designsystem.component

import android.text.TextUtils
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
import com.patrykandpatrick.vico.core.axis.formatter.DecimalFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.marker.DefaultMarkerLabelFormatter
import com.patrykandpatrick.vico.core.marker.MarkerLabelFormatter
import sv.ugm.sensormobile.ui.util.rememberChartStyle
import sv.ugm.sensormobile.ui.util.rememberLegend
import sv.ugm.sensormobile.ui.util.rememberMarker

@Composable
fun LineChart(
    entries: List<Map<Number, Number>>,
    modifier: Modifier = Modifier,
    entryTitleList: List<String>? = null,
    xAxisValueFormatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom> = DecimalFormatAxisValueFormatter(),
    yAxisValueFormatter: AxisValueFormatter<AxisPosition.Vertical.Start> = DecimalFormatAxisValueFormatter(),
    markerLabelFormatter: MarkerLabelFormatter = DefaultMarkerLabelFormatter(),
) {
    val chartEntryModelProducer = remember(entries) {
        ChartEntryModelProducer(
            entries.map { entry ->
                entry.map {
                    entryOf(
                        x = it.key,
                        y = it.value,
                    )
                }
            }
        )
    }
    
    val axisValuesOverrider = remember(entries) {
        object : AxisValuesOverrider<ChartEntryModel> {
            override fun getMaxY(model: ChartEntryModel): Float {
                return model.maxY + 2f
            }
            
            override fun getMinY(model: ChartEntryModel): Float {
                return model.minY - 5f
            }
        }
    }
    
    val xAxis = rememberBottomAxis(
        guideline = null,
        label = axisLabelComponent(
            verticalMargin = 4.dp,
            ellipsize = TextUtils.TruncateAt.MARQUEE,
        ),
        tick = null,
        valueFormatter = xAxisValueFormatter,
        labelRotationDegrees = 45f,
        itemPlacer = AxisItemPlacer.Horizontal.default(5),
    )
    
    val yAxis = rememberStartAxis(
        valueFormatter = yAxisValueFormatter,
    )
    
    val marker = rememberMarker(
        labelFormatter = markerLabelFormatter,
    )
    
    val legend = entryTitleList?.let {
        rememberLegend(
            entryTitleList = it,
        )
    }
    
    ProvideChartStyle(rememberChartStyle()) {
        Chart(
            chart = lineChart(
                axisValuesOverrider = axisValuesOverrider,
            ),
            chartModelProducer = chartEntryModelProducer,
            startAxis = yAxis,
            bottomAxis = xAxis,
            marker = marker,
            chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
            legend = legend,
            modifier = modifier
                .height(260.dp),
        )
    }
}

@Composable
fun LineChart(
    entry: Map<Number, Number>,
    modifier: Modifier = Modifier,
    entryTitle: String? = null,
    xAxisValueFormatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom> = DecimalFormatAxisValueFormatter(),
    yAxisValueFormatter: AxisValueFormatter<AxisPosition.Vertical.Start> = DecimalFormatAxisValueFormatter(),
    markerLabelFormatter: MarkerLabelFormatter = DefaultMarkerLabelFormatter(),
) {
    LineChart(
        entries = listOf(entry),
        entryTitleList = entryTitle?.let { listOf(it) },
        xAxisValueFormatter = xAxisValueFormatter,
        yAxisValueFormatter = yAxisValueFormatter,
        markerLabelFormatter = markerLabelFormatter,
        modifier = modifier,
    )
}