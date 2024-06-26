package com.example.taskmanagement_android.interfaces

import com.example.taskmanagement_android.model.LoginRequest
import com.example.taskmanagement_android.model.LoginResponse
import com.example.taskmanagement_android.model.RegisterRequest
import com.example.taskmanagement_android.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("users")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("auths")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}