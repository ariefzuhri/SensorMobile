package sv.ugm.sensormobile.data.mapper

import sv.ugm.sensormobile.data.source.local.provider.datastore.model.LoginSessionPreference
import sv.ugm.sensormobile.domain.model.LoginSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSessionDataMapper @Inject constructor() {
    
    fun mapDomainToData(input: LoginSession): LoginSessionPreference {
        return LoginSessionPreference(
            userId = input.userId,
        )
    }
    
    fun mapDataToDomain(input: LoginSessionPreference): LoginSession {
        return LoginSession(
            userId = input.userId ?: -1,
            isValid = input.userId != null,
        )
    }
    
}