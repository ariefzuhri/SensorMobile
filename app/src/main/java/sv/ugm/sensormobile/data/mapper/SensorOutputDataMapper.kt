package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.remote.dto.SensorOutputSnapshot
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toFloatOrZero
import sv.ugm.sensormobile.domain.util.toMillis
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputDataMapper @Inject constructor() {
    
    fun mapDataToDomain(
        input: List<SensorOutputSnapshot>,
    ): List<SensorOutput> {
        return input.map {
            mapDataToDomain(
                input = it,
            )
        }
    }
    
    private fun mapDataToDomain(
        input: SensorOutputSnapshot,
    ): SensorOutput {
        return SensorOutput(
            airQuality = input.airQuality?.toString().toFloatOrZero(),
            approxAltitude = input.approxAltitude?.toString().toFloatOrZero(),
            humidity = input.persentaseKelembapanTanah?.toString().toFloatOrZero(),
            light = input.light?.toString().toFloatOrZero(),
            pressure = input.pressure?.toString().toFloatOrZero(),
            raindrop = input.rainDrop?.toString().toFloatOrZero(),
            soilMoisture = input.h?.toString().toFloatOrZero(),
            temperature1 = input.t?.toString().toFloatOrZero(),
            temperature2 = input.temperature?.toString().toFloatOrZero(),
            timestampMillis = input.timestamp.toString()
                .toMillis(Constants.DateTimePatterns.Raw.TIMESTAMPS),
        )
    }
    
}