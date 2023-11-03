package sv.ugm.sensormobile.data.source.local.provider.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.data.source.local.model.LoginSessionEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSessionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    
    private object PreferenceKeys {
        
        val USER_ID = intPreferencesKey("user_id")
    }
    
    fun get(): Flow<LoginSessionEntity> {
        return dataStore.data
            .map { preferences ->
                LoginSessionEntity(
                    userId = preferences[PreferenceKeys.USER_ID],
                )
            }
    }
    
    suspend fun update(entity: LoginSessionEntity) {
        entity.userId?.let { userId ->
            dataStore.edit { preferences ->
                preferences[PreferenceKeys.USER_ID] = userId
            }
        }
    }
    
    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    
}