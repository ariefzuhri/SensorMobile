package sv.ugm.sensormobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sv.ugm.sensormobile.data.repository.AuthRepository
import sv.ugm.sensormobile.data.repository.SensorOutputRepository
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository

@Module(
    includes = [
        ApiModule::class,
        DataStoreModule::class,
        RoomModule::class,
    ],
)
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    abstract fun provideAuthRepository(
        authRepository: AuthRepository,
    ): IAuthRepository
    
    @Binds
    abstract fun provideSensorOutputRepository(
        sensorOutputRepository: SensorOutputRepository,
    ): ISensorOutputRepository
    
}