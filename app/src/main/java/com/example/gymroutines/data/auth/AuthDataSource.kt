package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User

interface AuthDataSource {
    suspend fun createUser(user : User) : Result<Unit>
    suspend fun login(email: String, password: String) : Result<Boolean>
    suspend fun logout()
}