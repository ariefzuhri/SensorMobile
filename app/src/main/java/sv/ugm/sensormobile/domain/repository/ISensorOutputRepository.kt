package sv.ugm.sensormobile.domain.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.util.Result

interface ISensorOutputRepository {
    
    suspend fun getSensorOutput(): Flow<Result<List<SensorOutput>>>
    
}