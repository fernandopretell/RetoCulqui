package com.fulbiopretell.retoculqui.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
)
