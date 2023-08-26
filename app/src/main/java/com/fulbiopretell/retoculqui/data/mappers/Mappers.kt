package com.fulbiopretell.retoculqui.data.mappers

import com.fulbiopretell.retoculqui.data.models.User
import com.fulbiopretell.retoculqui.data.models.UserResponse

// Mapea UserResponse a User
fun UserResponse?.toModel(): User {
    return User(
        id = this?.id,
        email = this?.email,
        first_name = this?.first_name,
        last_name = this?.last_name,
        avatar = this?.avatar
    )
}

// Mapea User a UserResponse
/*
fun User.toRequest(): UserResponse {
    return UserResponse(
        id = this.id,
        email = this.email,
        first_name = this.first_name,
        last_name = this.last_name,
        avatar = this.avatar
    )
}*/
