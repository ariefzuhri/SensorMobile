package sv.ugm.sensormobile.ui.route

sealed class Screen(val route: String) {
    
    data object Dashboard : Screen("dashboard")
    
    data object Login : Screen("login")
    
}