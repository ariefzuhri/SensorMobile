package sv.ugm.sensormobile.presentation.ui.screen.login

sealed class LoginEvent {
    
    data class OnUsernameChanged(
        val username: String,
    ) : LoginEvent()
    
    data class OnPasswordChanged(
        val password: String,
    ) : LoginEvent()
    
    data class LogIn(
        val username: String,
        val password: String,
    ) : LoginEvent()
    
}