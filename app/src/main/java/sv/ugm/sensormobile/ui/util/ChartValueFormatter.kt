package sv.ugm.sensormobile.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.ChartValues
import sv.ugm.sensormobile.domain.util.toDateTimeString

class DateTimeValueFormatter(
    private val datePattern: String,
    private val timePattern: String,
) : AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
    
    override fun formatValue(
        value: Float,
        chartValues: ChartValues,
    ): CharSequence {
        val dateTimeMillis = value.toLong()
        return dateTimeMillis.toDateTimeString("$datePattern\n$timePattern")
    }
    
}

@Composable
fun rememberValueFormatter(
    formatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom>,
): AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
    return remember { formatter }
}