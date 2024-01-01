package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import sv.ugm.sensormobile.domain.util.Constants.SENSOR_AUTO_UPDATE_INTERVAL_MINUTES
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import sv.ugm.sensormobile.domain.util.tickerFlow
import javax.inject.Inject

class PullSensorOutputsUseCase @Inject constructor(
    private val sensorOutputRepository: ISensorOutputRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Result<Unit>> {
        return tickerFlow(SENSOR_AUTO_UPDATE_INTERVAL_MINUTES).flatMapLatest {
            sensorOutputRepository.getSensorOutputsFromRemote()
                .map { result ->
                    result.mapWhenSuccess { sensorOutputs ->
                        sensorOutputRepository.replaceSensorOutputsLocal(
                            sensorOutputs
                        )
                    }
                }
        }
    }
    
}