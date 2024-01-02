package sv.ugm.sensormobile.presentation.ui.screen.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.CheckLoginSessionUseCase
import sv.ugm.sensormobile.domain.util.Result
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkLoginSessionUseCase: CheckLoginSessionUseCase,
) : ViewModel() {
    
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> get() = _state
    
    init {
        checkLoginSession()
    }
    
    private fun checkLoginSession() {
        viewModelScope.launch {
            checkLoginSessionUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isLoggedIn = result.data,
                        )
                    }
                    
                    else -> {}
                }
            }
        }
    }
    
}