package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.model.SensorData
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import javax.inject.Inject

class GetSensorDataUseCase @Inject constructor(
    private val sensorOutputRepository: ISensorOutputRepository,
) {
    
    suspend operator fun invoke(
        sensorDataType: SensorDataType,
    ): Flow<Result<List<SensorData>>> {
        return sensorOutputRepository.getSensorOutput()
            .map { result ->
                result.mapWhenSuccess { sensorOutputs ->
                    sensorOutputs
                        .map {
                            SensorData(
                                value = when (sensorDataType) {
                                    SensorDataType.AirQuality -> it.airQuality
                                    SensorDataType.ApproxAltitude -> it.approxAltitude
                                    SensorDataType.Humidity -> it.humidity
                                    SensorDataType.LightData -> it.light
                                    SensorDataType.PressureData -> it.pressure
                                    SensorDataType.Raindrop -> it.raindrop
                                    SensorDataType.SoilMoistureData -> it.soilMoisture
                                    SensorDataType.Temperature1 -> it.temperature1
                                    SensorDataType.Temperature2 -> it.temperature2
                                },
                                timestampMillis = it.timestampMillis,
                            )
                        }
                        .sortedBy {
                            it.timestampMillis
                        }
                }
            }
    }
    
}