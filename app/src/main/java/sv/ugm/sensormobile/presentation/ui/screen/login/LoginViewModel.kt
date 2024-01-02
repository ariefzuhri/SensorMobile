package sv.ugm.sensormobile.presentation.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.LogInUseCase
import sv.ugm.sensormobile.domain.util.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                )
            }
            
            is LoginEvent.OnPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                )
            }
            
            is LoginEvent.LogIn -> {
                logIn(
                    email = event.email,
                    password = event.password,
                )
            }
        }
    }
    
    private fun logIn(
        email: String,
        password: String,
    ) {
        _state.value = _state.value.copy(
            isLoading = true,
        )
        viewModelScope.launch {
            logInUseCase(
                email = email,
                password = password,
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }
                    
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            isSuccess = result.data.isValid,
                            isLoading = result.data.isValid,
                        )
                    }
                    
                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            isSuccess = false,
                            isLoading = false,
                            failureMessage = mutableStateOf(result.message),
                        )
                    }
                }
            }
        }
    }
    
}