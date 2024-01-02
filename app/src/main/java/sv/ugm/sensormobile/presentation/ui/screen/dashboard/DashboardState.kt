package sv.ugm.sensormobile.presentation.ui.screen.dashboard

import sv.ugm.sensormobile.domain.enums.SensorOutputUnit
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.model.SensorData
import sv.ugm.sensormobile.presentation.model.SensorSummary

data class DashboardState(
    val userName: String = "User",
    val sensorSummary: SensorSummary = SensorSummary(
        latestAirQuality = SensorData(
            type = SensorDataType.AirQuality,
            value = null,
            unit = SensorOutputUnit.AIR_QUALITY.unit,
        ),
        latestApproxAltitude = SensorData(
            type = SensorDataType.ApproxAltitude,
            value = null,
            unit = SensorOutputUnit.APPROX_ALTITUDE.unit,
        ),
        latestHumidity = SensorData(
            type = SensorDataType.Humidity,
            value = null,
            unit = SensorOutputUnit.HUMIDITY.unit,
        ),
        latestLight = SensorData(
            type = SensorDataType.Light,
            value = null,
            unit = SensorOutputUnit.LIGHT.unit,
        ),
        latestPressure = SensorData(
            type = SensorDataType.Pressure,
            value = null,
            unit = SensorOutputUnit.PRESSURE.unit,
        ),
        latestRaindrop = SensorData(
            type = SensorDataType.Raindrop,
            value = null,
            unit = SensorOutputUnit.RAINDROP.unit,
        ),
        latestSoilMoisture = SensorData(
            type = SensorDataType.SoilMoisture,
            value = null,
            unit = SensorOutputUnit.SOIL_MOISTURE.unit,
        ),
        latestTemperature1 = SensorData(
            type = SensorDataType.Temperature1,
            value = null,
            unit = SensorOutputUnit.TEMPERATURE_1.unit,
        ),
        latestTemperature2 = SensorData(
            type = SensorDataType.Temperature2,
            value = null,
            unit = SensorOutputUnit.TEMPERATURE_2.unit,
        ),
        latestTimestampMillis = null,
    ),
)