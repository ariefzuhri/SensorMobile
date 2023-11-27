package sv.ugm.sensormobile.ui.mapper

import androidx.annotation.StringRes
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.ui.model.ChartSeriesUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapDomainToUi(
        input: List<SensorRecord>,
        @StringRes label: Int,
    ): ChartSeriesUi {
        val chartEntries = mutableListOf<ChartSeriesUi.ChartEntry>()
        val xAxisLabels = mutableListOf<String>()
        val yAxisLabels = mutableListOf<String?>()
        
        input.forEachIndexed { index, it ->
            chartEntries.add(
                ChartSeriesUi.ChartEntry(
                    xValue = index.toFloat(),
                    xMarkerLabel = it.timestampMillis.toDateTimeString(
                        outputPattern = Constants.DateTimePatterns.Formatted.DATE + " " +
                                Constants.DateTimePatterns.Formatted.TIME
                    ),
                    yValue = it.value,
                    yMarkerLabel = null,
                )
            )
            
            xAxisLabels.add(
                it.timestampMillis.toDateTimeString(
                    outputPattern = Constants.DateTimePatterns.Formatted.SHORT_DATE + "\n" +
                            Constants.DateTimePatterns.Formatted.SHORT_TIME
                )
            )
            
            yAxisLabels.add(
                null
            )
        }
        
        return ChartSeriesUi(
            datasets = listOf(
                ChartSeriesUi.ChartDataset(
                    label = label,
                    entries = chartEntries,
                ),
            ),
            xAxisLabels = xAxisLabels,
            yAxisLabels = yAxisLabels,
        )
    }
    
}