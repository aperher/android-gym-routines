package com.example.gymroutines.data.chat





import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.model.Messages

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn


import javax.inject.Inject

class ChatDataSourceImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseFirestore):
    ChatDataSource {
    override fun getMessages(): Flow<MessagesDto> = callbackFlow {

        val query = db.collection("messages").document("messages")
        val registration = query.addSnapshotListener { snapshot, exception ->
        if (exception != null || snapshot == null) {
            return@addSnapshotListener
        }
        val messages = snapshot.toObject(MessagesDto::class.java)
            if (messages != null) {
                trySend(messages)
            }
    }

    awaitClose { registration.remove() }
}.flowOn(Dispatchers.IO)
}
