package com.example.gymroutines.data.profile

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
}