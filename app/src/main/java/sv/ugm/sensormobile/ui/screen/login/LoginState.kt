package sv.ugm.sensormobile.ui.screen.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
)