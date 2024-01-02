package sv.ugm.sensormobile.presentation.ui.route

sealed class Screen(val route: String) {
    
    data object Chart : Screen("chart")
    
    data object Dashboard : Screen("dashboard")
    
    data object Login : Screen("login")
    
    data object Splash : Screen("splash")
    
}