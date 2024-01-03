package sv.ugm.sensormobile.data.source.local

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.source.local.provider.room.dao.SensorOutputDao
import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity
import sv.ugm.sensormobile.data.util.LocalResult
import sv.ugm.sensormobile.data.util.invokeLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputLocalDataSource @Inject constructor(
    private val sensorOutputDao: SensorOutputDao,
) {
    
    fun getSensorOutputs(): Flow<LocalResult<List<SensorOutputEntity>>> {
        return sensorOutputDao.getAll().invokeLocal()
    }
    
    suspend fun insertSensorOutputs(sensorOutputEntities: List<SensorOutputEntity>) {
        sensorOutputDao.insert(sensorOutputEntities)
    }
    
    suspend fun clearSensorOutputs() {
        sensorOutputDao.clear()
    }
    
}