package sv.ugm.sensormobile.data.source.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.source.remote.dto.SensorOutputSnapshot
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.data.util.addValueEventListenerFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputRemoteDataSource @Inject constructor(
    private val db: FirebaseDatabase,
) {
    
    private val sensorOutputRef by lazy {
        db.getReference("sensor1")
    }
    
    suspend fun getSensorOutput(): Flow<RemoteResult<List<SensorOutputSnapshot>>> {
        return sensorOutputRef.addValueEventListenerFlow(
            SensorOutputSnapshot::class.java
        )
    }
    
}