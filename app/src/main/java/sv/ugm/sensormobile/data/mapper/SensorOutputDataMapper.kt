package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity
import sv.ugm.sensormobile.data.source.remote.api.response.SensorOutputsResponse
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toFloatOrZero
import sv.ugm.sensormobile.domain.util.toMillis
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputDataMapper @Inject constructor() {
    
    fun mapDataToDomain(
        input: List<SensorOutputEntity>,
    ): List<SensorOutput> {
        return input.map {
            mapDataToDomain(
                input = it,
            )
        }
    }
    
    private fun mapDataToDomain(
        input: SensorOutputEntity,
    ): SensorOutput {
        return SensorOutput(
            airQuality = input.airQuality,
            approxAltitude = input.approxAltitude,
            humidity = input.humidity,
            light = input.light,
            pressure = input.pressure,
            raindrop = input.raindrop,
            soilMoisture = input.soilMoisture,
            temperature1 = input.temperature1,
            temperature2 = input.temperature2,
            timestampMillis = input.timestampMillis,
        )
    }
    
    fun mapDataToDomain(
        input: SensorOutputsResponse,
    ): List<SensorOutput> {
        return input.data?.map {
            mapDataToDomain(
                input = it,
            )
        } ?: emptyList()
    }
    
    private fun mapDataToDomain(
        input: SensorOutputsResponse.DataItem?,
    ): SensorOutput {
        return SensorOutput(
            airQuality = input?.airQuality.toFloatOrZero(),
            approxAltitude = input?.approxAltitude.toFloatOrZero(),
            humidity = input?.h.toFloatOrZero(),
            light = input?.light.toFloatOrZero(),
            pressure = input?.pressure.toFloatOrZero(),
            raindrop = input?.rainDrop.toFloatOrZero(),
            soilMoisture = input?.persentaseKelembapanTanah.toFloatOrZero(),
            temperature1 = input?.t.toFloatOrZero(),
            temperature2 = input?.temperature.toFloatOrZero(),
            timestampMillis = input?.timeAdded.toString()
                .toMillis(Constants.DateTimePatterns.Raw.TIMESTAMPS),
        )
    }
    
    fun mapDomainToData(
        input: List<SensorOutput>,
    ): List<SensorOutputEntity> {
        return input.map {
            mapDomainToData(
                input = it,
            )
        }
    }
    
    private fun mapDomainToData(
        input: SensorOutput,
    ): SensorOutputEntity {
        return SensorOutputEntity(
            airQuality = input.airQuality,
            approxAltitude = input.approxAltitude,
            humidity = input.humidity,
            light = input.light,
            pressure = input.pressure,
            raindrop = input.raindrop,
            soilMoisture = input.soilMoisture,
            temperature1 = input.temperature1,
            temperature2 = input.temperature2,
            timestampMillis = input.timestampMillis,
        )
    }
    
}