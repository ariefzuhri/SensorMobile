package sv.ugm.sensormobile.ui.mapper

import androidx.annotation.StringRes
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.ui.model.ChartDataUi
import sv.ugm.sensormobile.ui.model.ChartDataset
import sv.ugm.sensormobile.ui.model.ChartEntry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapToChartData(
        input: List<SensorRecord>,
        @StringRes label: Int,
    ): ChartDataUi {
        val chartEntries = mutableListOf<ChartEntry>()
        val xAxisLabelList = mutableListOf<String>()
        val yAxisLabelList = mutableListOf<String?>()
        
        input.forEachIndexed { index, it ->
            chartEntries.add(
                ChartEntry(
                    xValue = index.toFloat(),
                    xMarkerLabel = it.timestampMillis.toDateTimeString(
                        outputPattern = Constants.DateTimePatterns.Formatted.DATE + " " +
                                Constants.DateTimePatterns.Formatted.TIME
                    ),
                    yValue = it.value,
                    yMarkerLabel = null,
                )
            )
            
            xAxisLabelList.add(
                it.timestampMillis.toDateTimeString(
                    outputPattern = Constants.DateTimePatterns.Formatted.SHORT_DATE + "\n" +
                            Constants.DateTimePatterns.Formatted.SHORT_TIME
                )
            )
            
            yAxisLabelList.add(
                null
            )
        }
        
        return ChartDataUi(
            datasets = listOf(
                ChartDataset(
                    label = label,
                    entries = chartEntries,
                ),
            ),
            xAxisLabelList = xAxisLabelList,
            yAxisLabelList = yAxisLabelList,
        )
    }
    
}