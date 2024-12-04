package com.example.taskapp.data.repositary_impl

import com.example.taskapp.domain.entity.UserEntity
import com.example.taskapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firestore:FirebaseFirestore
) : AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signUp(userEntity: UserEntity,password:String): Result<UserEntity> {
        return try {
            // Create user in Firebase Authentication
            val authResult = firebaseAuth.createUserWithEmailAndPassword(userEntity.email, password).await()
            val firebaseUser = authResult.user ?: throw Exception("User creation failed")

            firestore.collection("user_table").document(firebaseUser.uid).set(userEntity).await()
            Result.success(userEntity)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }


    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {

            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw Exception("User login failed")

            val userDocument = firestore.collection("user_table").document(firebaseUser.uid).get().await()
            if (userDocument.exists()) {
                val userEntity = userDocument.toObject(UserEntity::class.java)
                    ?: throw Exception("Failed to parse user data")
                Result.success(userEntity)
            } else {
                throw Exception("User not found in database")
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }


    override suspend fun logOut(): Result<Boolean> {
        return try {
            firebaseAuth.signOut()
            Result.success(true)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun getCurrentUser(): Result<UserEntity?> {
        return try {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {

                val userDocument = firestore.collection("user_table").document(firebaseUser.uid).get().await()
                if (userDocument.exists()) {
                    val userEntity = userDocument.toObject(UserEntity::class.java)
                    Result.success(userEntity)
                } else {
                    Result.success(firebaseUserToUserEntity(firebaseUser))
                }
            } else {
                Result.success(null) // No user is signed in
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }


    override suspend fun resetPassword(email: String): Result<Boolean> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(true)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    private fun firebaseUserToUserEntity(firebaseUser: FirebaseUser): UserEntity {
        return UserEntity(
            email = firebaseUser.email ?: "No Email", // Default to a placeholder if email is null
            name = firebaseUser.displayName ?: "Unknown", // Use the display name or a fallback
            profilePictureUrl = firebaseUser.photoUrl?.toString(), // Convert URI to String if available
            location = null, // Default to null (fetch from Firestore or update later)
            role = "unknown" // Default role; adjust based on your application's logic
        )
    }

}
