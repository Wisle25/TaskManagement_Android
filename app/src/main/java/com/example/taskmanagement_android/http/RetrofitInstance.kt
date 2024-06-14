package com.example.taskmanagement_android.http

import com.example.taskmanagement_android.interfaces.TaskAPI
import com.example.taskmanagement_android.interfaces.UserAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://taskmanagementbe-production.up.railway.app/"

    val userApi: UserAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    val taskApi: TaskAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskAPI::class.java)
    }
}
