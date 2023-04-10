package com.example.gymroutines.data.profile

import com.example.gymroutines.data.profile.model.toDomain
import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class ProfilerepositoryImpl @Inject constructor(val datasource: ProfileDataSource) : ProfileRepository {
    override suspend fun getUser() : Result<User> {
        return datasource.getUser()
    }
}