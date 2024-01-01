package sv.ugm.sensormobile.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sv.ugm.sensormobile.data.source.local.provider.room.AppRoomDatabase
import sv.ugm.sensormobile.data.source.local.provider.room.dao.SensorOutputDao
import javax.inject.Singleton

private const val DATABASE_NAME = "sensor_mobile_db"

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    
    @Singleton
    @Provides
    fun provideRoomDatabase(
        application: Application,
    ): AppRoomDatabase {
        return Room.databaseBuilder(
            application,
            AppRoomDatabase::class.java,
            DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }
    
    @Singleton
    @Provides
    fun provideSensorOutputDao(db: AppRoomDatabase): SensorOutputDao {
        return db.sensorOutputDao()
    }
    
}