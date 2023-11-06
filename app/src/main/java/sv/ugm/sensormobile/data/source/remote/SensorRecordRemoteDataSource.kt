package sv.ugm.sensormobile.data.source.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.source.remote.dto.SensorRecordSnapshot
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.data.util.addValueEventListenerFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordRemoteDataSource @Inject constructor(
    private val db: FirebaseDatabase,
) {
    
    private val sensorRecordsRef by lazy {
        db.getReference("sensor1")
    }
    
    suspend fun getSensorRecords(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
        return sensorRecordsRef.addValueEventListenerFlow(
            SensorRecordSnapshot::class.java
        )
    }
    
}