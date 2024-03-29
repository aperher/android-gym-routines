package com.example.gymroutines.data.profile

import com.example.gymroutines.model.User

interface ProfileRepository {
    suspend fun getUser(): Result<User>
    suspend fun getUserName(): String
    suspend fun getImageUrl(): String
    suspend fun updateUserName(userName: String, currentUser: User): Result<Boolean>
}