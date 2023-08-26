package com.fulbiopretell.retoculqui.data.repository

import com.fulbiopretell.retoculqui.data.UserRemoteDataSource
import com.fulbiopretell.retoculqui.data.mappers.toModel
import com.fulbiopretell.retoculqui.data.models.DataResponse
import com.fulbiopretell.retoculqui.data.models.User
import com.google.gson.Gson
import com.limapps.mitrueque2.preferences.Prefs
import javax.inject.Inject

class UserRepository @Inject constructor(private val dataSource: UserRemoteDataSource) {

    suspend fun loginUser(email: String, password: String) = dataSource.loginUser(email, password)

    suspend fun registerUser(email: String, password: String): Boolean {
        val response = dataSource.registerUser(email, password)
        if (response.isSuccessful){
            val getDataUserId = response.body()?.id
            val responseDataUser = dataSource.getDataUser(getDataUserId.toString())
            if (responseDataUser.isSuccessful){
                val dataUser = responseDataUser.body() as DataResponse
                Prefs.user_data = Gson().toJson(dataUser.data.toModel())
                return true
            }
        }
        return false
    }

    fun getUserSaved(email: String) : User?{
        val user = Gson().fromJson(Prefs.user_data, User::class.java)
        if(user != null && user.email.equals(email)) return user else return null
    }
}
