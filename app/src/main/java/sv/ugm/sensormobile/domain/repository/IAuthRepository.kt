package sv.ugm.sensormobile.domain.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.domain.model.LoginSession

interface IAuthRepository {
    
    suspend fun login(
        email: String,
        password: String,
    ): Flow<LoginSession>
    
    suspend fun storeLoginSession(
        loginSession: LoginSession,
    )
    
    suspend fun getLoginSession(): Flow<LoginSession>
    
    suspend fun clearLoginSession()
    
}