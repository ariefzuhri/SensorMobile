package sv.ugm.sensormobile.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val LOGIN_SESSION_DATA_STORE_NAME = "login_session_prefs"

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    
    @Singleton
    @Provides
    fun provideLoginSessionDataStore(
        application: Application,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler {
                emptyPreferences()
            },
            migrations = listOf(
                SharedPreferencesMigration(
                    context = application,
                    sharedPreferencesName = LOGIN_SESSION_DATA_STORE_NAME,
                ),
            ),
            scope = CoroutineScope((Dispatchers.IO + SupervisorJob())),
            produceFile = {
                application.preferencesDataStoreFile(
                    name = LOGIN_SESSION_DATA_STORE_NAME,
                )
            },
        )
    }
    
}