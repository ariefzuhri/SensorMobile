package sv.ugm.sensormobile.data.source.local.provider

import sv.ugm.sensormobile.data.source.local.model.UserStatic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserData @Inject constructor() {
    
    private val userList = listOf(
        UserStatic(
            id = 1,
            email = "sensormobile@mail.com",
            password = "sensormobile",
        )
    )
    
    fun getAll(): List<UserStatic> {
        return userList
    }
    
}