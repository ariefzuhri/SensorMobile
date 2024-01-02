package sv.ugm.sensormobile.data.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.mapper.LoginSessionDataMapper
import sv.ugm.sensormobile.data.source.local.AuthLocalDataSource
import sv.ugm.sensormobile.data.source.local.provider.datastore.model.LoginSessionPreference
import sv.ugm.sensormobile.data.util.LocalResource
import sv.ugm.sensormobile.data.util.LocalResult
import sv.ugm.sensormobile.domain.model.LoginSession
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val dataMapper: LoginSessionDataMapper,
) : IAuthRepository {
    
    override suspend fun logIn(
        email: String,
        password: String,
    ): Flow<Result<LoginSession>> {
        return object : LocalResource<LoginSessionPreference, LoginSession>() {
            override suspend fun createCall(): Flow<LocalResult<LoginSessionPreference>> {
                return localDataSource.logIn(
                    email = email,
                    password = password,
                )
            }
            
            override suspend fun onFetchSuccess(data: LoginSessionPreference): LoginSession {
                return dataMapper.mapDataToDomain(data)
            }
        }.asFlow()
    }
    
    override suspend fun storeLoginSession(loginSession: LoginSession) {
        val preference = dataMapper.mapDomainToData(loginSession)
        localDataSource.updateLoginSession(
            preference = preference,
        )
    }
    
    override suspend fun getLoginSession(): Flow<Result<LoginSession>> {
        return object : LocalResource<LoginSessionPreference, LoginSession>() {
            override suspend fun createCall(): Flow<LocalResult<LoginSessionPreference>> {
                return localDataSource.getLoginSession()
            }
            
            override suspend fun onFetchSuccess(data: LoginSessionPreference): LoginSession {
                return dataMapper.mapDataToDomain(data)
            }
        }.asFlow()
    }
    
    override suspend fun clearLoginSession() {
        localDataSource.clearLoginSession()
    }
    
}