package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {
    override suspend fun register(user: User): Boolean = dataSource.createUser(user)

    override suspend fun login(email: String, password: String): Boolean =
        dataSource.login(email, password)

    override fun logout() {
        dataSource.logout()
    }
}