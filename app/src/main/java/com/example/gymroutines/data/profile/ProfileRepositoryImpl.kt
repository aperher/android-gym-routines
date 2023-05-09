package com.example.gymroutines.data.profile

import com.example.gymroutines.data.profile.model.toDomain
import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(val datasource: ProfileDataSource) :
    ProfileRepository {
    override suspend fun getUser(): Result<User> {
        return datasource.getUser()
    }

    override suspend fun getUserName(): String {
        var Username =""
        runBlocking() {
            getUser().fold(onSuccess = {
                Username =  it.username
            }, onFailure = {
                TODO()
            })

        }
        return Username;
    }

    override suspend fun updateUserName(userName: String, currentuser: User): Result<Boolean> {
        return datasource.updateUserName(userName, currentuser)
    }
}