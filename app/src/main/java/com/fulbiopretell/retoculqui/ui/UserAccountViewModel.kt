package com.fulbiopretell.retoculqui.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.retoculqui.data.models.LoginResponse
import com.fulbiopretell.retoculqui.data.models.User
import com.fulbiopretell.retoculqui.domain.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAccountViewModel  @Inject constructor(private val usersUseCase: UsersUseCase): ViewModel() {

    val email = mutableStateOf("")
    val name = mutableStateOf("")
    val password = mutableStateOf("")

    private val _userSaved = MutableLiveData<User?>()
    val userSaved: LiveData<User?> get() = _userSaved

    private val _errorUserSaved = MutableLiveData<String>()
    val errorUserSaved: LiveData<String> get() = _errorUserSaved

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> get() = _userLogged


    fun onEmailChanged(newEmail: String) {
        email.value = newEmail
    }

    fun onNameChanged(newEmail: String) {
        name.value = newEmail
    }

    fun onPasswordChanged(newEmail: String) {
        password.value = newEmail
    }

    fun userIsSaved(email: String){
        viewModelScope.launch {
            val response = usersUseCase.getUserSaved(email)
            if (response != null) {
                _userSaved.postValue(response)
            } else {
                _errorUserSaved.postValue("El usuario no existe.")
            }
        }
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                usersUseCase.registerUser(email, password)
                _userLogged.postValue(true) // Suponiendo que _userLogged es un MutableLiveData<Boolean>
            } catch (e: Exception) {
                _userLogged.postValue(false)
            }
        }
    }
}