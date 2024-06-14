package com.example.taskmanagement_android.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)

data class RegisterResponse(
    val status: String,
    val message: String,
    val data: String
)

data class LoginRequest(
    val identity: String,
    val password: String
)

data class LoginResponse(
    val status: String,
    val message: String,
    val data: String?
)
