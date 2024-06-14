package com.example.taskmanagement_android.model

data class TaskRequest(
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val dueDate: String,
    val userId: String,
)