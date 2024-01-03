package sv.ugm.sensormobile.presentation.mapper

import androidx.annotation.StringRes
import sv.ugm.sensormobile.domain.model.SensorData
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.presentation.model.ChartSeries
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputPresentationMapper @Inject constructor() {
    
    fun mapDomainToPresentation(
        input: List<SensorData>,
        @StringRes label: Int,
        unit: String,
    ): ChartSeries {
        val chartEntries = mutableListOf<ChartSeries.ChartEntry>()
        val xAxisLabels = mutableListOf<String>()
        val yAxisLabels = mutableListOf<String?>()
        
        input.forEachIndexed { index, it ->
            chartEntries.add(
                ChartSeries.ChartEntry(
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
        
        return ChartSeries(
            datasets = listOf(
                ChartSeries.ChartDataset(
                    label = label,
                    entries = chartEntries,
                ),
            ),
            xAxisLabels = xAxisLabels,
            xAxisTitle = null,
            yAxisLabels = yAxisLabels,
            yAxisTitle = unit,
        )
    }
    
}