package sv.ugm.sensormobile.presentation.mapper

import sv.ugm.sensormobile.domain.enums.SensorOutputUnit
import sv.ugm.sensormobile.domain.model.SensorData
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.presentation.model.ChartSeries
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputPresentationMapper @Inject constructor() {
    
    fun mapDomainToPresentation(
        input: List<SensorData>,
        sensorDataType: SensorDataType,
    ): ChartSeries {
        val chartEntries = mutableListOf<ChartSeries.ChartEntry>()
        
        val xAxisLabels = mutableListOf<String>()
        
        val yAxisLabels = mutableListOf<String?>()
        val yAxisTitle = when (sensorDataType) {
            SensorDataType.AirQuality -> SensorOutputUnit.AIR_QUALITY
            SensorDataType.ApproxAltitude -> SensorOutputUnit.APPROX_ALTITUDE
            SensorDataType.Humidity -> SensorOutputUnit.HUMIDITY
            SensorDataType.Light -> SensorOutputUnit.LIGHT
            SensorDataType.Pressure -> SensorOutputUnit.PRESSURE
            SensorDataType.Raindrop -> SensorOutputUnit.RAINDROP
            SensorDataType.SoilMoisture -> SensorOutputUnit.SOIL_MOISTURE
            SensorDataType.Temperature1 -> SensorOutputUnit.TEMPERATURE_1
            SensorDataType.Temperature2 -> SensorOutputUnit.TEMPERATURE_2
        }.unit
        
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
                    entries = chartEntries,
                ),
            ),
            xAxisLabels = xAxisLabels,
            xAxisTitle = null,
            yAxisLabels = yAxisLabels,
            yAxisTitle = yAxisTitle,
        )
    }
    
}