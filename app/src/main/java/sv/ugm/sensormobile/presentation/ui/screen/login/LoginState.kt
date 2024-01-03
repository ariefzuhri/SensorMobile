package sv.ugm.sensormobile.presentation.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import sv.ugm.sensormobile.BuildConfig

data class LoginState(
    val appVersion: String = "v${BuildConfig.VERSION_NAME}",
    val email: String = "",
    val password: String = "",
    
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)