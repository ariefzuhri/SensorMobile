package sv.ugm.sensormobile.presentation.ui.route

import sv.ugm.sensormobile.presentation.util.Constants

sealed class Screen(val route: String) {
    
    data object Chart : Screen("chart/{${Constants.Extras.SENSOR_DATA_TYPE_ID}}") {
        
        fun createRoute(sensorDataTypeId: Int) = "chart/${sensorDataTypeId}"
        
    }
    
    data object Dashboard : Screen("dashboard")
    
    data object Login : Screen("login")
    
    data object Splash : Screen("splash")
    
}