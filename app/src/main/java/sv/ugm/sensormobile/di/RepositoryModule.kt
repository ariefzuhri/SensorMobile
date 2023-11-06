package sv.ugm.sensormobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sv.ugm.sensormobile.data.repository.AuthRepository
import sv.ugm.sensormobile.data.repository.SensorRecordRepository
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.repository.ISensorRecordRepository

@Module(
    includes = [
        DataStoreModule::class,
        FirebaseModule::class,
    ],
)
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class RepositoryModule {
    
    @Binds
    abstract fun provideAuthRepository(
        authRepository: AuthRepository,
    ): IAuthRepository
    
    @Binds
    abstract fun provideSensorRecordRepository(
        sensorRecordRepository: SensorRecordRepository,
    ): ISensorRecordRepository
    
}