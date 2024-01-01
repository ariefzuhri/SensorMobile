package sv.ugm.sensormobile.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.data.source.local.provider.room.dao.SensorOutputDao
import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity
import sv.ugm.sensormobile.data.util.LocalResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputLocalDataSource @Inject constructor(
    private val sensorOutputDao: SensorOutputDao,
) {
    
    fun getSensorOutputs(): Flow<LocalResult<List<SensorOutputEntity>>> {
        return sensorOutputDao.getAll().map {
            LocalResult.Success(it)
        }
    }
    
    suspend fun insertSensorOutputs(sensorOutputEntities: List<SensorOutputEntity>) {
        sensorOutputDao.insert(sensorOutputEntities)
    }
    
    suspend fun clearSensorOutputs() {
        sensorOutputDao.clear()
    }
    
}