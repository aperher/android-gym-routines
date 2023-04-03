package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(user : User): Result<Unit>
    suspend fun logout()
}