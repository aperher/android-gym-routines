package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User

interface AuthDataSource {
    suspend fun createUser(user : User) : Boolean
    suspend fun login(email: String, password: String) : Boolean
    fun logout()
}