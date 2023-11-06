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
            .map { userStaticList ->
                val user = userStaticList.find {
                    it.email == email &&
                            it.password == password
                }
                LoginSession(
                    userId = user?.id ?: -1,
                    isValid = user != null,
                )
            }
    }
    
    override suspend fun storeLoginSession(loginSession: LoginSession) {
        val preference = dataMapper.mapDomainToData(loginSession)
        localDataSource.updateLoginSession(
            preference = preference,
        )
    }
    
    override suspend fun getLoginSession(): Flow<LoginSession> {
        return localDataSource.getLoginSession()
            .map { preference ->
                dataMapper.mapDataToDomain(preference)
            }
    }
    
    override suspend fun clearLoginSession() {
        localDataSource.clearLoginSession()
    }
    
}