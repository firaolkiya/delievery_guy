package com.example.taskapp.domain.repository

import com.example.taskapp.domain.entity.Location
import com.example.taskapp.domain.entity.UserEntity

import kotlinx.coroutines.flow.Flow

interface ClientRepository {


    fun getLocation(email: String): Flow<Location?>


    fun updateLocation(location: Location): Boolean

    fun getAllUsers(): Flow<List<UserEntity>>


    fun sendMessage(message: String, user: UserEntity): Boolean
}
