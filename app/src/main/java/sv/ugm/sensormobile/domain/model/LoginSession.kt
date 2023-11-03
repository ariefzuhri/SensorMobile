package sv.ugm.sensormobile.domain.model

data class LoginSession(
    val userId: Int,
    val isValid: Boolean,
)