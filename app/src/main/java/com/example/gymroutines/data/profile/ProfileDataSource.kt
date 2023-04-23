package com.example.gymroutines.data.profile

import com.example.gymroutines.data.profile.model.UserDto
import com.example.gymroutines.model.User

interface ProfileDataSource {
    suspend fun getUser() : Result<User>
    suspend fun updateAllUserData(userName: String, password: String)
    suspend fun updateUserName(userName: String): Result<Boolean>
    suspend fun updateUserPassword(password: String): Result<Boolean>
}