package sv.ugm.sensormobile.domain.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.domain.model.LoginSession
import sv.ugm.sensormobile.domain.util.Result

interface IAuthRepository {
    
    suspend fun login(
        email: String,
        password: String,
    ): Flow<Result<LoginSession>>
    
    suspend fun storeLoginSession(
        loginSession: LoginSession,
    )
    
    suspend fun getLoginSession(): Flow<Result<LoginSession>>
    
    suspend fun clearLoginSession()
    
}