package sv.ugm.sensormobile.presentation.mapper

import sv.ugm.sensormobile.domain.enums.SensorOutputUnit
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.presentation.model.SensorData
import sv.ugm.sensormobile.presentation.model.SensorSummary
import javax.inject.Inject
import javax.inject.Singleton
import sv.ugm.sensormobile.domain.model.SensorSummary as SensorSummaryDomain

@Singleton
class SensorSummaryPresentationMapper @Inject constructor() {
    
    fun mapDomainToPresentation(
        input: SensorSummaryDomain,
    ): SensorSummary {
        return SensorSummary(
            latestAirQuality = SensorData(
                type = SensorDataType.AirQuality,
                value = input.latestAirQuality,
                unit = SensorOutputUnit.AIR_QUALITY.unit,
            ),
            latestApproxAltitude = SensorData(
                type = SensorDataType.ApproxAltitude,
                value = input.latestApproxAltitude,
                unit = SensorOutputUnit.APPROX_ALTITUDE.unit,
            ),
            latestHumidity = SensorData(
                type = SensorDataType.Humidity,
                value = input.latestHumidity,
                unit = SensorOutputUnit.HUMIDITY.unit,
            ),
            latestLight = SensorData(
                type = SensorDataType.Light,
                value = input.latestLight,
                unit = SensorOutputUnit.LIGHT.unit,
            ),
            latestPressure = SensorData(
                type = SensorDataType.Pressure,
                value = input.latestPressure,
                unit = SensorOutputUnit.PRESSURE.unit,
            ),
            latestRaindrop = SensorData(
                type = SensorDataType.Raindrop,
                value = input.latestRaindrop,
                unit = SensorOutputUnit.RAINDROP.unit,
            ),
            latestSoilMoisture = SensorData(
                type = SensorDataType.SoilMoisture,
                value = input.latestSoilMoisture,
                unit = SensorOutputUnit.SOIL_MOISTURE.unit,
            ),
            latestTemperature1 = SensorData(
                type = SensorDataType.Temperature1,
                value = input.latestTemperature1,
                unit = SensorOutputUnit.TEMPERATURE_1.unit,
            ),
            latestTemperature2 = SensorData(
                type = SensorDataType.Temperature2,
                value = input.latestTemperature2,
                unit = SensorOutputUnit.TEMPERATURE_2.unit,
            ),
            latestTimestampMillis = input.latestTimestampMillis.toDateTimeString(
                outputPattern = Constants.DateTimePatterns.Formatted.DATE + " " +
                        Constants.DateTimePatterns.Formatted.SHORT_TIME
            ),
        )
    }
    
}