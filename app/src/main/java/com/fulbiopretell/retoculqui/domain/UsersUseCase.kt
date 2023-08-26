package com.fulbiopretell.retoculqui.domain

import com.fulbiopretell.retoculqui.data.repository.UserRepository
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun loginUser(email: String, password: String) = repository.loginUser(email, password)

    suspend fun registerUser(email: String, password: String) = repository.registerUser(email, password)

    fun getUserSaved(email: String) = repository.getUserSaved(email)
}
