package sv.ugm.sensormobile.presentation.ui.screen.dashboard

sealed class DashboardEvent {
    
    data object LogOut : DashboardEvent()
    
}