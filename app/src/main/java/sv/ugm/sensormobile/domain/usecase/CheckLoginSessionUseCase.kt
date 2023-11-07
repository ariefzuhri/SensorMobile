package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import javax.inject.Inject

class CheckLoginSessionUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
) {
    
    suspend operator fun invoke(): Flow<Result<Boolean>> {
        return authRepository.getLoginSession()
            .map { result ->
                result.mapWhenSuccess {
                    it.isValid
                }
            }
    }
    
}