package sv.ugm.sensormobile.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class LoginState(
    val email: String = "",
    val password: String = "",
    
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)