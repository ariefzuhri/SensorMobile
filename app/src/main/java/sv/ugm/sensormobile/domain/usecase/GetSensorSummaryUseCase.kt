package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.model.SensorSummary
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import javax.inject.Inject

class GetSensorSummaryUseCase @Inject constructor(
    private val sensorOutputRepository: ISensorOutputRepository,
) {
    
    suspend operator fun invoke(): Flow<Result<SensorSummary>> {
        return sensorOutputRepository.getSensorOutputsFromLocal()
            .map { result ->
                result.mapWhenSuccess { sensorOutputs ->
                    sensorOutputs
                        .maxBy {
                            it.timestampMillis
                        }
                        .let {
                            SensorSummary(
                                latestAirQuality = it.airQuality,
                                latestApproxAltitude = it.approxAltitude,
                                latestHumidity = it.humidity,
                                latestLight = it.light,
                                latestPressure = it.pressure,
                                latestRaindrop = it.raindrop,
                                latestSoilMoisture = it.soilMoisture,
                                latestTemperature1 = it.temperature1,
                                latestTemperature2 = it.temperature2,
                                latestTimestampMillis = it.timestampMillis,
                            )
                        }
                }
            }
    }
    
}