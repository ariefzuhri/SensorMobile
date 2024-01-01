package sv.ugm.sensormobile.data.source.local.provider.room

import androidx.room.Database
import androidx.room.RoomDatabase
import sv.ugm.sensormobile.data.source.local.provider.room.dao.SensorOutputDao
import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity

@Database(
    entities = [
        SensorOutputEntity::class,
    ],
    version = 1,
)
abstract class AppRoomDatabase : RoomDatabase() {
    
    abstract fun sensorOutputDao(): SensorOutputDao
    
}