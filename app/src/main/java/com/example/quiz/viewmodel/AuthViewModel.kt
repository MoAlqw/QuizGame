package com.example.quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.User
import com.example.domain.usecase.user.LoginUserUseCase
import com.example.domain.usecase.user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _authResult = MutableStateFlow<Result<User>?>(null)
    val authResult: StateFlow<Result<User>?> = _authResult.asStateFlow()

    fun onEmailChanged(value: String) {
        email.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun tryLogin() {
        viewModelScope.launch {
            try {
                val user = loginUserUseCase(email.value, password.value)
                if (user != null) {
                    _authResult.value = Result.success(user)
                } else {
                    _authResult.value = Result.failure(Exception("Неверный логин или пароль"))
                }
            } catch (e: Exception) {
                _authResult.value = Result.failure(e)
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            val emailValue = email.value
            val passwordValue = password.value
            try {
                val newUser = User(email = emailValue, password = passwordValue)
                registerUserUseCase(newUser)
                _authResult.value = Result.success(newUser)
            } catch (e: Exception) {
                _authResult.value = Result.failure(e)
            }
        }
    }
}