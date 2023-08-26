package com.fulbiopretell.retoculqui.data.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("id")
    val id: Int
)
