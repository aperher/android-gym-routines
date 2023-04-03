package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {
    override suspend fun register(user: User): Result<Unit> = dataSource.createUser(user)

    override suspend fun login(email: String, password: String): Result<Boolean> =
        dataSource.login(email, password)

    override suspend fun logout() {
        dataSource.logout()
    }
}