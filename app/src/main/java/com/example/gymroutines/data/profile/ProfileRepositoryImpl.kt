package com.example.gymroutines.data.profile

import com.example.gymroutines.model.User
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
    override suspend fun getImageUrl(): String {
        var ImageUrl =""
        runBlocking() {
            getUser().fold(onSuccess = {
                ImageUrl =  it.imageUrl
            }, onFailure = {
                TODO()
            })

        }
        return ImageUrl;
    }

    override suspend fun updateUserName(userName: String, currentUser: User): Result<Boolean> {
        return datasource.updateUserName(userName, currentUser)
    }
}