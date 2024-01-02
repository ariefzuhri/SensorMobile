package sv.ugm.sensormobile.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.data.source.local.provider.datastore.LoginSessionDataStore
import sv.ugm.sensormobile.data.source.local.provider.datastore.model.LoginSessionPreference
import sv.ugm.sensormobile.data.source.local.provider.statics.UserData
import sv.ugm.sensormobile.data.util.LocalResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor(
    private val loginSessionDataStore: LoginSessionDataStore,
    private val userData: UserData,
) {
    
    // Dummy login
    suspend fun logIn(
        email: String,
        password: String,
    ): Flow<LocalResult<LoginSessionPreference>> {
        return flow {
            val userList = userData.getAll()
            val loggedInUser = userList.find {
                it.email == email && it.password == password
            }
            
            if (loggedInUser != null) {
                val preference = LoginSessionPreference(
                    userId = loggedInUser.id,
                )
                emit(LocalResult.Success(preference))
            } else {
                emit(
                    LocalResult.Failure(
                        Exception("Invalid email or password")
                    )
                )
            }
        }
    }
    
    fun getLoginSession(): Flow<LocalResult<LoginSessionPreference>> {
        return loginSessionDataStore.get().map {
            LocalResult.Success(it)
        }
    }
    
    suspend fun updateLoginSession(preference: LoginSessionPreference) {
        loginSessionDataStore.update(preference)
    }
    
    suspend fun clearLoginSession() {
        loginSessionDataStore.clear()
    }
    
}