package com.example.gymroutines.data.profile

import android.database.CursorJoiner
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.gymroutines.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseFirestore) : ProfileDataSource {
    override suspend fun getUser() : Result<User> = runCatching{
        val result = db.collection("users").document(auth.currentUser?.email!!).get().await()
        result.toObject(User::class.java)!!
    }

    override suspend fun updateAllUserData(userName: String, password: String){
        /*val currentUser = auth.currentUser
        val authDb = FirebaseAuth.getInstance()*/

        //return Result<Boolean>(true)
    }

    override suspend fun updateUserName(userName: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserPassword(password: String): Result<Boolean> {
        TODO("Not yet implemented")
    }
}