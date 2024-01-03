package sv.ugm.sensormobile.presentation.ui.screen.splash

import sv.ugm.sensormobile.BuildConfig

data class SplashState(
    val appVersion: String = "v${BuildConfig.VERSION_NAME}",
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean? = null,
)