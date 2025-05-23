package com.alberto.habits.authentication.presentation

sealed interface LoginEvent {
    data class EmailChange(val email: String) : LoginEvent
    data class PasswordChange(val password: String) : LoginEvent
    object Login : LoginEvent
    //object SignUp : LoginEvent
}