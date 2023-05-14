package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthDataSource {
    companion object {
        const val USER_COLLECTION = "users"
    }

    override suspend fun createUser(user: User): Boolean = runCatching {
        val account = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        if (account != null)
            db.collection(USER_COLLECTION).document(user.email).set(user).await()
    }.isSuccess

    override suspend fun login(email: String, password: String): Boolean = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
    }.isSuccess

    override fun logout() = auth.signOut()

    override fun getUser(): String = auth.currentUser?.email ?: ""
}