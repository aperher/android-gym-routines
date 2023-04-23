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

    override suspend fun updateAllUserData(userName: String, password: String) {
        //return datasource.updateAllUserData(userName,password)
    }

    override suspend fun updateUserName(userName: String): Result<Boolean> {
        return datasource.updateUserName(userName)
    }

    override suspend fun updateUserPassword(password: String): Result<Boolean> {
        return datasource.updateUserPassword(password)
    }
}