package com.example.gymroutines.data.profile

import com.example.gymroutines.model.User

interface ProfileRepository {
    suspend fun getUser(): Result<User>

    suspend fun getUserName(): String
}