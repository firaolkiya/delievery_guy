package com.example.taskapp.domain.entity

data class UserEntity(
    val email: String,              // Email
    val name: String,
    val profilePictureUrl: String? = null,
    val location: Location? = null,
    val role: String = "delivery_guy",
)
