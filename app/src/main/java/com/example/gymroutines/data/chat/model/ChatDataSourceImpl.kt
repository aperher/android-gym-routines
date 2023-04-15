package com.example.gymroutines.data.chat.model

import com.example.gymroutines.model.Messages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatDataSourceImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseFirestore): ChatDataSource {
    override suspend fun getMessages(): Result<Messages> = runCatching {
        val result = db.collection("messages").document(auth.currentUser?.email!!).get().await()
        result.toObject(Messages::class.java)!!
    }
}
