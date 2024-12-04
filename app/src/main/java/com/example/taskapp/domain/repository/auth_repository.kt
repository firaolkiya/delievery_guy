package com.example.taskapp.domain.repository

import com.example.taskapp.domain.entity.UserEntity

interface AuthRepository {
    // Method for signing up a user
    suspend fun signUp(userEntity: UserEntity,password: String): Result<UserEntity>

    // Method for logging in a user
    suspend fun login(email: String, password: String): Result<UserEntity>

    // Method for logging out a user
    suspend fun logOut(): Result<Boolean>

    // Method to get the current authenticated user
    suspend fun getCurrentUser(): Result<UserEntity?>

    // Method for resetting password
    suspend fun resetPassword(email: String): Result<Boolean>

    // Other authentication-related functions can be added here
}
