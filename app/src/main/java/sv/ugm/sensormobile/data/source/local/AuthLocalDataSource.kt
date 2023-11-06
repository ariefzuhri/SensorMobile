package sv.ugm.sensormobile.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sv.ugm.sensormobile.data.source.local.model.LoginSessionPreference
import sv.ugm.sensormobile.data.source.local.model.UserStatic
import sv.ugm.sensormobile.data.source.local.provider.LoginSessionDataStore
import sv.ugm.sensormobile.data.source.local.provider.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor(
    private val loginSessionDataStore: LoginSessionDataStore,
    private val userData: UserData,
) {
    
    suspend fun getUserList(): Flow<List<UserStatic>> {
        return flow {
            val userList = userData.getAll()
            emit(userList)
        }
    }
    
    fun getLoginSession(): Flow<LoginSessionPreference> {
        return loginSessionDataStore.get()
    }
    
    suspend fun updateLoginSession(preference: LoginSessionPreference) {
        loginSessionDataStore.update(preference)
    }
    
    suspend fun clearLoginSession() {
        loginSessionDataStore.clear()
    }
    
}