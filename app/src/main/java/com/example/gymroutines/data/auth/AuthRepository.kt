package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(user : User): Boolean

    fun getUser() : String
    fun logout()
}