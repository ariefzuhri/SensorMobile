package sv.ugm.sensormobile.data.source.local.provider.statics

import sv.ugm.sensormobile.data.source.local.provider.statics.model.UserStatic
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