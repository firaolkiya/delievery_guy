package com.example.taskapp.data.repositary_impl

import com.example.taskapp.domain.entity.Location
import com.example.taskapp.domain.entity.UserEntity
import com.example.taskapp.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow

class ClientRepositoryImpl :ClientRepository{
    override fun getLocation(email: String): Flow<Location?> {
        TODO("Not yet implemented")
    }

    override fun updateLocation(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): Flow<List<UserEntity>> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String, user: UserEntity): Boolean {
        TODO("Not yet implemented")
    }
}