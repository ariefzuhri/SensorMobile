package sv.ugm.sensormobile.data.source.local.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensor_outputs")
data class SensorOutputEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    
    @ColumnInfo(name = "air_quality")
    val airQuality: Float,
    
    @ColumnInfo(name = "approx_altitude")
    val approxAltitude: Float,
    
    @ColumnInfo(name = "humidity")
    val humidity: Float,
    
    @ColumnInfo(name = "light")
    val light: Float,
    
    @ColumnInfo(name = "pressure")
    val pressure: Float,
    
    @ColumnInfo(name = "raindrop")
    val raindrop: Float,
    
    @ColumnInfo(name = "soil_moisture")
    val soilMoisture: Float,
    
    @ColumnInfo(name = "temperature1")
    val temperature1: Float,
    
    @ColumnInfo(name = "temperature2")
    val temperature2: Float,
    
    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long,
)