package sv.ugm.sensormobile.presentation.ui.screen.login

sealed class LoginEvent {
    
    data class OnEmailChanged(
        val email: String,
    ) : LoginEvent()
    
    data class OnPasswordChanged(
        val password: String,
    ) : LoginEvent()
    
    data class LogIn(
        val email: String,
        val password: String,
    ) : LoginEvent()
    
}