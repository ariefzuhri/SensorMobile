package sv.ugm.sensormobile.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sv.ugm.sensormobile.data.source.local.model.LoginSessionEntity
import sv.ugm.sensormobile.data.source.local.model.UserEntity
import sv.ugm.sensormobile.data.source.local.provider.preference.LoginSessionDataStore
import sv.ugm.sensormobile.data.source.local.provider.statics.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor(
    private val loginSessionDataStore: LoginSessionDataStore,
    private val userData: UserData,
) {
    
    suspend fun getUserList(): Flow<List<UserEntity>> {
        return flow {
            val userList = userData.getAll()
            emit(userList)
        }
    }
    
    fun getLoginSession(): Flow<LoginSessionEntity> {
        return loginSessionDataStore.get()
    }
    
    suspend fun updateLoginSession(entity: LoginSessionEntity) {
        loginSessionDataStore.update(entity)
    }
    
    suspend fun clearLoginSession() {
        loginSessionDataStore.clear()
    }
    
}