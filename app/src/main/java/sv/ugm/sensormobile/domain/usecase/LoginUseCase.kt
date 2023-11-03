package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.model.LoginSession
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
) {
    
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Flow<LoginSession> {
        return authRepository.login(
            email = email,
            password = password,
        ).map { loginSession ->
            if (loginSession.isValid) {
                authRepository.storeLoginSession(
                    loginSession = loginSession,
                )
            }
            loginSession
        }
    }
    
}