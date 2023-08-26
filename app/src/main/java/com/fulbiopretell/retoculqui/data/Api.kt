package com.fulbiopretell.retoculqui.data

import com.fulbiopretell.retoculqui.data.models.DataResponse
import com.fulbiopretell.retoculqui.data.models.LoginRequest
import com.fulbiopretell.retoculqui.data.models.LoginResponse
import com.fulbiopretell.retoculqui.data.models.RegisterRequest
import com.fulbiopretell.retoculqui.data.models.RegisterResponse
import com.fulbiopretell.retoculqui.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("users/{id}")
    suspend fun getDataUser(@Path("id")  id: String): Response<DataResponse>
}