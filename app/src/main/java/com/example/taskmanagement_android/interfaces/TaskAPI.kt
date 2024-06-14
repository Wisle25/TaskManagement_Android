package com.example.taskmanagement_android.interfaces

import com.example.taskmanagement_android.model.RegisterResponse
import com.example.taskmanagement_android.model.TaskRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskAPI {
    @POST("tasks")
    fun addTask(@Body task: TaskRequest): Call<RegisterResponse>
}