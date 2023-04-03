package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthDataSource {
    companion object {
        const val USER_COLLECTION = "users"
    }

    override suspend fun createUser(user: User): Result<Unit> = runCatching {
        val account = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        if (account != null) {
            db.collection(USER_COLLECTION).document(user.email).set(user).await()
            true
        } else
            false
    }

    override suspend fun login(email: String, password: String): Result<Boolean> = runCatching {
        val account = auth.signInWithEmailAndPassword(email, password).await()
        account != null && account.user != null
        /*
        // Login a través de Firebase
        val user = db.collection(USER_COLLECTION).document(email).get().await().toObject<User>()
        if (user == null || user.password != password)
            throw FirebaseFirestoreException(
                "Incorrect mail or password",
                FirebaseFirestoreException.Code.PERMISSION_DENIED
            )
         */
    }

    override suspend fun logout() = auth.signOut()

    private suspend fun sendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()?.await()
    }
}