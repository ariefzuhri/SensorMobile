package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.local.model.LoginSessionEntity
import sv.ugm.sensormobile.domain.model.LoginSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSessionDataMapper @Inject constructor(){
    
    fun mapDomainToEntity(input: LoginSession): LoginSessionEntity {
        return LoginSessionEntity(
            userId = input.userId,
        )
    }
    
    fun mapEntityToDomain(input: LoginSessionEntity): LoginSession {
        return LoginSession(
            userId = input.userId ?: -1,
            isValid = input.userId != null,
        )
    }
    
}