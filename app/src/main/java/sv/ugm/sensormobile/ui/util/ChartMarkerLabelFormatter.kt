package sv.ugm.sensormobile.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.core.chart.values.ChartValues
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.core.marker.MarkerLabelFormatter
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString

class SensorRecordMarkerLabelFormatter : MarkerLabelFormatter {
    
    @Suppress("UnnecessaryVariable")
    override fun getLabel(
        markedEntries: List<Marker.EntryModel>,
        chartValues: ChartValues,
    ): CharSequence {
        val x = markedEntries.first().entry.x
        val y = markedEntries.first().entry.y
        
        val dateTimeMillis = x.toLong()
        val value = y
        
        val line1 = dateTimeMillis.toDateTimeString(
            "${Constants.DateTimePatterns.Formatted.DATE} ${Constants.DateTimePatterns.Formatted.TIME}"
        )
        val line2 = value
        
        return "$line1\n$line2"
    }
    
}

@Composable
fun rememberMarkerLabelFormatter(
    formatter: MarkerLabelFormatter,
): MarkerLabelFormatter {
    return remember { formatter }
}