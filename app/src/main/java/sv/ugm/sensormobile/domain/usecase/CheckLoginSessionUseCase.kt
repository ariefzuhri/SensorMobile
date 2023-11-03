package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import javax.inject.Inject

class CheckLoginSessionUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
) {
    
    suspend operator fun invoke(): Flow<Boolean> {
        return authRepository.getLoginSession()
            .map { loginSession ->
                loginSession.isValid
            }
    }
    
}