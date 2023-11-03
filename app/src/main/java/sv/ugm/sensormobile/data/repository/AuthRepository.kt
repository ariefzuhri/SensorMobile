package sv.ugm.sensormobile.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.data.mapper.LoginSessionDataMapper
import sv.ugm.sensormobile.data.source.local.AuthLocalDataSource
import sv.ugm.sensormobile.domain.model.LoginSession
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val dataMapper: LoginSessionDataMapper,
) : IAuthRepository {
    
    override suspend fun login(
        email: String,
        password: String,
    ): Flow<LoginSession> {
        return localDataSource.getUserList()
            .map { userList ->
                val user = userList.find { it.email == email && it.password == password }
                LoginSession(
                    userId = user?.id ?: -1,
                    isValid = user != null,
                )
            }
    }
    
    override suspend fun storeLoginSession(loginSession: LoginSession) {
        val entity = dataMapper.mapDomainToEntity(loginSession)
        localDataSource.updateLoginSession(
            entity = entity,
        )
    }
    
    override suspend fun getLoginSession(): Flow<LoginSession> {
        return localDataSource.getLoginSession()
            .map { entity ->
                dataMapper.mapEntityToDomain(entity)
            }
    }
    
    override suspend fun clearLoginSession() {
        localDataSource.clearLoginSession()
    }
    
}