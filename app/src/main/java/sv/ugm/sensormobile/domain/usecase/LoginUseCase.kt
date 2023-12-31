package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.model.LoginSession
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
) {
    
    suspend operator fun invoke(
        username: String,
        password: String,
    ): Flow<Result<LoginSession>> {
        return authRepository.logIn(
            username = username,
            password = password,
        ).map { result ->
            result.mapWhenSuccess {
                authRepository.storeLoginSession(it)
                it
            }
        }
    }
    
}