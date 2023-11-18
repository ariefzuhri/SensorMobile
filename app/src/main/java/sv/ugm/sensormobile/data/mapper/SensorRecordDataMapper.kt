package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.remote.dto.SensorRecordSnapshot
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.SensorType
import sv.ugm.sensormobile.domain.util.toFloatOrZero
import sv.ugm.sensormobile.domain.util.toMillis
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapDataToDomain(
        input: List<SensorRecordSnapshot>,
        sensorType: SensorType,
    ): List<SensorRecord> {
        return input.map {
            mapDataToDomain(
                input = it,
                sensorType = sensorType,
            )
        }
    }
    
    private fun mapDataToDomain(
        input: SensorRecordSnapshot,
        sensorType: SensorType,
    ): SensorRecord {
        return SensorRecord(
            value = when (sensorType) {
                SensorType.AirQualitySensor -> input.airQuality
                SensorType.Altimeter -> input.approxAltitude
                SensorType.Hygrometer -> input.h
                SensorType.Photodetector -> input.light
                SensorType.PressureSensor -> input.pressure
                SensorType.RainGauge -> input.rainDrop
                SensorType.SoilMoistureSensor -> input.persentaseKelembapanTanah
                SensorType.Thermometer -> input.temperature
            }?.toString().toFloatOrZero(),
            timestampMillis = input.timestamp.toString()
                .toMillis(Constants.DateTimePatterns.Raw.TIMESTAMPS),
        )
    }
    
}