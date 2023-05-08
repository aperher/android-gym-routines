package com.example.gymroutines.data.chat

import androidx.lifecycle.viewModelScope
import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.model.Messages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.okhttp.internal.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import java.util.*
import java.util.logging.Level

class ChatDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val profile: ProfileRepository
) : ChatDataSource {
    var userName  = ""
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
            val id = randomString(20)
            val message = Messages(userName, text, id)
        Platform.logger.log(Level.INFO, message.id.toString())
        db.collection("messages").document("messages").update("messages", FieldValue.arrayUnion(message))
    }.isSuccess

    private fun randomString(longitud: Int): String {
        val caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val sb = StringBuilder(longitud)

        for (i in 0 until longitud) {
            val indice = random.nextInt(caracteresPermitidos.length)
            sb.append(caracteresPermitidos[indice])
        }
        return sb.toString()
    }
    init {
        runBlocking {
          userName = profile.getUserName()
        }
    }
}
