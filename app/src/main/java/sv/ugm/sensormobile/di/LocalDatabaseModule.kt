package sv.ugm.sensormobile.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    
    private val Context.loginSessionDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "login_session"
    )
    
    @Singleton
    @Provides
    fun provideLoginSessionDataStore(application: Application): DataStore<Preferences> {
        return application.loginSessionDataStore
    }
    
}