package com.example.gymroutines.data.chat

import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.model.Messages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.okhttp.internal.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import java.util.*
import java.util.logging.Level

class ChatDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) :
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

    override fun createMessage(text: String): Boolean = runCatching {
        Platform.logger.log(Level.INFO, "dentro del datasoruce")
            val userId = auth.currentUser?.email
            val id = randomstring()
            val message = Messages(userId!!, text, id)
        db.collection("messages").document("messages").update("messages", message)

    }.isSuccess


    fun randomstring(): String {
        val random = Random()
        val randomNum = random.nextInt(1000000000000000.toInt())
        return  String.format("%015d", randomNum)

    }
}
