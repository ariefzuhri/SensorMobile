package sv.ugm.sensormobile.data.source.local.provider

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.data.source.local.model.LoginSessionPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSessionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    
    private object PreferenceKeys {
        
        val USER_ID = intPreferencesKey("user_id")
        
    }
    
    fun get(): Flow<LoginSessionPreference> {
        return dataStore.data
            .map { pref ->
                LoginSessionPreference(
                    userId = pref[PreferenceKeys.USER_ID],
                )
            }
    }
    
    suspend fun update(preference: LoginSessionPreference) {
        preference.userId?.let { userId ->
            dataStore.edit { pref ->
                pref[PreferenceKeys.USER_ID] = userId
            }
        }
    }
    
    suspend fun clear() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }
    
}