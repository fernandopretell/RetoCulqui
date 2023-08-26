package com.fulbiopretell.retoculqui.data

import com.fulbiopretell.retoculqui.data.models.LoginRequest
import com.fulbiopretell.retoculqui.data.models.RegisterRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val api: Api) {

    suspend fun loginUser(email: String, password: String) = api.loginUser(LoginRequest(email, password))

    suspend fun registerUser(email: String, password: String) = api.registerUser(RegisterRequest(email, password))

    suspend fun getDataUser(id: String) = api.getDataUser(id)
}
