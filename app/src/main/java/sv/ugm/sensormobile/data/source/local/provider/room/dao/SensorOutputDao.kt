package sv.ugm.sensormobile.data.source.local.provider.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity

@Dao
interface SensorOutputDao {
    
    @Query("SELECT * FROM sensor_outputs")
    fun getAll(): Flow<List<SensorOutputEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sensorOutputEntities: List<SensorOutputEntity>)
    
    @Query("DELETE FROM sensor_outputs")
    suspend fun clear()
    
}