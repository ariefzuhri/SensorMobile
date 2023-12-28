package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.remote.dto.SensorOutputResponse
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toFloatOrZero
import sv.ugm.sensormobile.domain.util.toMillis
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputDataMapper @Inject constructor() {
    
    fun mapDataToDomain(
        input: SensorOutputResponse,
    ): List<SensorOutput> {
        return input.data?.map {
            mapDataToDomain(
                input = it,
            )
        } ?: emptyList()
    }
    
    private fun mapDataToDomain(
        input: SensorOutputResponse.DataItem?,
    ): SensorOutput {
        return SensorOutput(
            airQuality = input?.airQuality.toFloatOrZero(),
            approxAltitude = input?.approxAltitude.toFloatOrZero(),
            humidity = input?.persentaseKelembapanTanah.toFloatOrZero(),
            light = input?.light.toFloatOrZero(),
            pressure = input?.pressure.toFloatOrZero(),
            raindrop = input?.rainDrop.toFloatOrZero(),
            soilMoisture = input?.h.toFloatOrZero(),
            temperature1 = input?.t.toFloatOrZero(),
            temperature2 = input?.temperature.toFloatOrZero(),
            timestampMillis = input?.timeAdded.toString()
                .toMillis(Constants.DateTimePatterns.Raw.TIMESTAMPS),
        )
    }
    
}